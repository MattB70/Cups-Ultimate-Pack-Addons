package com.mattborle.cupsaddons.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class ElectricityParticles extends TextureSheetParticle {
    protected ElectricityParticles(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet spriteset, double xd, double yd, double zd) {
        super(level, xCoord, yCoord, zCoord, xd, yd, zd);
        this.friction = 0.2f;
        this.xd = xd;       // velocities
        this.yd = yd;
        this.zd = zd;
        this.quadSize *= 2.5f;
        this.lifetime = 3; // in ticks
        this.setSpriteFromAge(spriteset);
        this.rCol = (float)Math.random();     // color in rgb
        this.gCol = (float)Math.random();
        this.bCol = 1f;
    }
    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }
    private void fadeOut() {
        this.alpha = (-(1/(float)lifetime)*age+1);
    }
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            return new ElectricityParticles(level, x,y,z, this.sprites, dx,dy,dz);
        }
    }
}
