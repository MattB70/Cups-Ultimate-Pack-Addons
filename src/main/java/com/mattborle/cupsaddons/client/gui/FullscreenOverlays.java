package com.mattborle.cupsaddons.client.gui;

import com.mattborle.cupsaddons.handlers.overlay.GamingDisplayOverlayIngame;
import com.mattborle.cupsaddons.handlers.overlay.TransportingDisplayOverlayIngame;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class FullscreenOverlays {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void eventHandler(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            int w = event.getWindow().getGuiScaledWidth();
            int h = event.getWindow().getGuiScaledHeight();
            int posX = w / 2;
            int posY = h / 2;
            Level _world = null;
            double _x = 0;
            double _y = 0;
            double _z = 0;
            Player entity = Minecraft.getInstance().player;
            if (entity != null) {
                _world = entity.level;
                _x = entity.getX();
                _y = entity.getY();
                _z = entity.getZ();
            }
            Level world = _world;
            double x = _x;
            double y = _y;
            double z = _z;
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                    GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            RenderSystem.setShaderColor(1, 1, 1, 1);


            if (TransportingDisplayOverlayIngame.execute(entity)) {
                RenderSystem.setShaderTexture(0, new ResourceLocation("cupsaddons:textures/screens/transporting_overlay.png"));
                Minecraft.getInstance().gui.blit(event.getMatrixStack(), 0, 0, 0, 0, w, h, w, h);
            }
            if (GamingDisplayOverlayIngame.execute(entity)) {
                RenderSystem.setShaderTexture(0, new ResourceLocation("cupsaddons:textures/screens/gamer_glasses.png"));
                Minecraft.getInstance().gui.blit(event.getMatrixStack(), 0, 0, 0, 0, w, h, w, h);
            }


            RenderSystem.depthMask(true);
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();
            RenderSystem.setShaderColor(1, 1, 1, 1);
        }
    }
}
