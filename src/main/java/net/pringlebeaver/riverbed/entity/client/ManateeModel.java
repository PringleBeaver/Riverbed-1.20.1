package net.pringlebeaver.riverbed.entity.client;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.pringlebeaver.riverbed.entity.custom.ManateeEntity;
import org.jetbrains.annotations.NotNull;

public class ManateeModel<T extends ManateeEntity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private float headXRot;
	private float eatAnimationScale;
	private final ModelPart left_flipper;
	private final ModelPart right_flipper;
	private final ModelPart body;
	private final ModelPart head;
	private final ModelPart flowers;

	private final ModelPart tail;
	private final ModelPart root;
	private final ModelPart tail_back;

	public ManateeModel(ModelPart pRoot) {
		this.root = pRoot;
		this.body = root.getChild("body");
		this.flowers = this.body.getChild("flowers");
		this.left_flipper = body.getChild("left_flipper");
		this.right_flipper = body.getChild("right_flipper");
		this.head = body.getChild("head");
		this.tail = body.getChild("tail");
		this.tail_back = tail.getChild("tail_back");

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -18.0F, -1.0F, 20.0F, 20.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -12.0F));

		PartDefinition flowers = body.addOrReplaceChild("flowers", CubeListBuilder.create(), PartPose.offset(0.0F, -10.0F, 10.0F));

		PartDefinition flower4_r1 = flowers.addOrReplaceChild("flower4_r1", CubeListBuilder.create().texOffs(46, 76).addBox(0.0F, 1.0F, -5.5F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -16.0F, 7.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition flower4_r2 = flowers.addOrReplaceChild("flower4_r2", CubeListBuilder.create().texOffs(46, 76).addBox(-0.5F, -2.0F, -4.5F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.9393F, -13.0F, 6.4393F, 0.0F, 0.7854F, 0.0F));

		PartDefinition flower3_r1 = flowers.addOrReplaceChild("flower3_r1", CubeListBuilder.create().texOffs(46, 76).addBox(-0.5F, -2.0F, -4.5F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0607F, -14.0F, -10.5607F, 0.0F, 0.7854F, 0.0F));

		PartDefinition flower3_r2 = flowers.addOrReplaceChild("flower3_r2", CubeListBuilder.create().texOffs(46, 76).addBox(0.0F, 1.0F, -5.5F, 0.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -17.0F, -9.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition flower2_r1 = flowers.addOrReplaceChild("flower2_r1", CubeListBuilder.create().texOffs(44, 55).addBox(0.0F, -5.0F, -5.5F, 0.0F, 14.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -15.0F, 7.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition flower2_r2 = flowers.addOrReplaceChild("flower2_r2", CubeListBuilder.create().texOffs(44, 55).addBox(0.0F, -5.0F, -5.5F, 0.0F, 14.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -15.0F, 7.5F, 0.0F, 0.7854F, 0.0F));

		PartDefinition flower1_r1 = flowers.addOrReplaceChild("flower1_r1", CubeListBuilder.create().texOffs(44, 55).addBox(0.0F, -5.0F, -5.5F, 0.0F, 14.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -15.0F, -4.5F, 0.0F, 0.7854F, 0.0F));

		PartDefinition flower1_r2 = flowers.addOrReplaceChild("flower1_r2", CubeListBuilder.create().texOffs(44, 55).addBox(0.0F, -5.0F, -5.5F, 0.0F, 14.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -15.0F, -4.5F, 0.0F, -0.7854F, 0.0F));

		PartDefinition right_flipper = body.addOrReplaceChild("right_flipper", CubeListBuilder.create(), PartPose.offset(-10.0F, 1.0F, 6.0F));

		PartDefinition flipper_r = right_flipper.addOrReplaceChild("flipper_r1", CubeListBuilder.create().texOffs(45, 44).addBox(-7.8192F, -1.5736F, -3.0F, 10.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition left_flipper = body.addOrReplaceChild("left_flipper", CubeListBuilder.create(), PartPose.offset(10.0F, 1.0F, 6.0F));

		PartDefinition flipper_l = left_flipper.addOrReplaceChild("flipper_r2", CubeListBuilder.create().texOffs(45, 44).mirror().addBox(-2.0F, -1.0F, -4.0F, 10.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.7854F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(64, 5).addBox(-6.0F, -4.0F, 0.0F, 12.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 23.0F));

		PartDefinition tail_back = tail.addOrReplaceChild("tail_back", CubeListBuilder.create().texOffs(44, 51).addBox(-8.0F, -1.0F, -2.0F, 16.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 44).addBox(-6.0F, -3.35F, -9.0F, 12.0F, 13.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 67).addBox(-6.0F, -0.35F, -13.0F, 12.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.65F, 0.0F, 0.3491F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void prepareMobModel(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick) {
		super.prepareMobModel(pEntity, pLimbSwing, pLimbSwingAmount, pPartialTick);
		// Here we use pPartialTick to get the smooth value and store it
		this.eatAnimationScale = pEntity.getHeadEatAngleScale(pPartialTick);
	}

	@Override
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		float BABY_HEAD_SCALE = 1.5F;
		float BABY_BODY_SCALE = 0.35F;
		if (this.young) {
			root.xScale = BABY_BODY_SCALE;
			root.yScale = BABY_BODY_SCALE;
			root.zScale = BABY_BODY_SCALE;
			root.y = 15F;

			head.xScale = BABY_HEAD_SCALE;
			head.yScale = BABY_HEAD_SCALE;
			head.zScale = BABY_HEAD_SCALE;
		} else {
			root.y = 0F;

			root.xScale = 1.0F;
			root.yScale = 1.0F;
			root.zScale = 1.0F;

			head.xScale = 1.0F;
			head.yScale = 1.0F;
			head.zScale = 1.0F;
		}

		this.body.xRot = pHeadPitch * ((float) Math.PI / 180F) / 2;
		this.head.xRot = (pHeadPitch * ((float) Math.PI / 180F) / 4) + 0.35f;
		this.head.yRot = pNetHeadYaw * ((float) Math.PI / 180F);

		float eatAnimationScale = pEntity.getHeadEatAngleScale(pAgeInTicks);

		this.head.xRot += this.eatAnimationScale * (15.0F * ((float)Math.PI / 180F));

		this.head.y = -9.65F + (this.eatAnimationScale * 0.6F);

		this.right_flipper.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 2.0F * pLimbSwingAmount * 0.5F;
		this.left_flipper.xRot = Mth.cos(pLimbSwing * 0.6662F) * 2.0F * pLimbSwingAmount * 0.5F;
		if (pEntity.isInWater()) {
			this.body.xRot += -0.05F - 0.05F * Mth.cos(pAgeInTicks * 0.15F);
			this.tail.xRot = -0.1F * Mth.cos(pAgeInTicks * 0.15F);
			this.tail_back.xRot = -0.2F * Mth.cos(pAgeInTicks * 0.15F);
		}
	}

	@Override
	public @NotNull ModelPart root() {
		return this.root;
	}

}