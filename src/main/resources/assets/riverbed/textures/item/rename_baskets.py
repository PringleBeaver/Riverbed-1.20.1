import os
import re
import sys
import uuid

# === CONFIG ===
folder = r"D:\MinecraftModding\riverbed 1.20.1\src\main\resources\assets\riverbed\textures\item\basket_patterns"
extensions = [".png", ".jpg", ".jpeg"]  # file types you want to rename
dry_run = False  # True = only print the plan, False = actually rename
# ==========================================

# Base patterns
patterns = [
    "stripe_basket_fragment",
    "dots_basket_fragment",
    "double_stripe_basket_fragment",
    "zigzag_basket_fragment",
    "solid_basket_fragment"
]

colors = [
    "red", "orange", "yellow", "lime", "cyan", "light_blue",
    "blue", "purple", "magenta", "pink", "brown", "black",
    "gray", "light_gray", "white", "green"
]

# Build the 85 new names
new_names = []
new_names.extend(patterns)  # first 5
for color in colors:
    for pat in patterns:
        new_names.append(f"{color}_{pat}")

# Collect matching files
files = [f for f in os.listdir(folder) if os.path.splitext(f)[1].lower() in extensions]
if not files:
    print("No files found in", folder)
    sys.exit(1)

# Extract number at end of filename (before extension)
def extract_number(filename):
    m = re.search(r'(\d+)(?=\.[^.]+$)', filename)
    return int(m.group(1)) if m else float('inf')

files.sort(key=extract_number)

# Build mapping
n = min(len(files), len(new_names))
if len(files) != len(new_names):
    print(f"WARNING: {len(files)} files found but {len(new_names)} new names available. Will rename first {n} files.")

mapping = []
for i in range(n):
    old = files[i]
    ext = os.path.splitext(old)[1]
    new_basename = new_names[i]
    mapping.append((old, new_basename + ext))

# Show planned renames
print("Planned renames:")
for i, (old, new) in enumerate(mapping, 1):
    print(f"{i:02d}. {old} -> {new}")

if dry_run:
    print("\nDry run only — no files renamed. Set dry_run = False to actually rename.")
    sys.exit(0)

# === Do the renaming safely (two-pass) ===
temp_names = []
try:
    # Step 1: rename to unique temporary names
    for old, _ in mapping:
        ext = os.path.splitext(old)[1]
        tmp = "__tmp__" + uuid.uuid4().hex + ext
        os.rename(os.path.join(folder, old), os.path.join(folder, tmp))
        temp_names.append(tmp)

    # Step 2: rename temps to final desired names
    for tmp, (_, final) in zip(temp_names, mapping):
        os.rename(os.path.join(folder, tmp), os.path.join(folder, final))

    print("\nRename completed successfully.")
except Exception as e:
    print("ERROR during rename:", e)
    raise
