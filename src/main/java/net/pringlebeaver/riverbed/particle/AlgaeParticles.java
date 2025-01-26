package net.pringlebeaver.riverbed.particle;

import com.ibm.icu.text.MessagePattern;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.SpriteCoordinateExpander;
import net.minecraft.core.particles.SimpleParticleType;

public class AlgaeParticles extends TextureSheetParticle {
    private final float rotSpeed;

    protected AlgaeParticles(ClientLevel pLevel, double pX, double pY, double pZ, SpriteSet spriteSet, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);

        this.friction = 1.0F;
        this.xd = pXSpeed;
        this.yd = pYSpeed  + (double)(this.random.nextFloat() / 100.0F);
        this.zd = pZSpeed;
        this.lifetime = 240;
        this.setSpriteFromAge(spriteSet);
        this.scale(0.25F);
        this.rotSpeed = ((float)Math.random() - 0.5F) * 0.05F;
        this.roll = (float)Math.random() * ((float)Math.PI * 2F);

    }

    @Override
    public void tick() {
        super.tick();

        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            fadeOut();
            this.oRoll = this.roll;
            this.roll += (float)Math.PI * this.rotSpeed * 2.0F;
        }
    }

    private void fadeOut() {
        if (age > 40) {
            this.alpha = (-(1/(float)lifetime) * (age -40)+ 1);
        }


    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new AlgaeParticles(level, pX, pY, pZ,this.spriteSet, pXSpeed, pYSpeed, pZSpeed);
        }
    }

    }
