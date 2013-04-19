package com.dmillerw.wac.client.gui.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSpecialButton extends GuiButton {
    
    public GuiSpecialButton(int par1, int par2, int par3, String par4Str) {
        this(par1, par2, par3, 200, 20, par4Str);
    }

    public GuiSpecialButton(int par1, int par2, int par3, int par4, int par5, String par6Str) {
       super(par1, par2, par3, par4, par5, par6Str);
    }

    @Override
    public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
        if (this.drawButton) {
            FontRenderer fontrenderer = par1Minecraft.fontRenderer;
            par1Minecraft.renderEngine.bindTexture("/gui/gui.png");
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_82253_i = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            int k = this.getHoverState(this.field_82253_i);
            this.mouseDragged(par1Minecraft, par2, par3);
            int l = 14737632;

            if (!this.enabled) {
                l = -6250336;
            } else if (this.field_82253_i) {
                l = 16777120;
            }
            
            drawBackgroundAndText(fontrenderer, k, l);
            draw();
        }
    }

    public void draw() {}
    
    public void drawBackgroundAndText(FontRenderer font, int hoverState, int color) {
    	this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + hoverState * 20, this.width / 2, this.height);
        this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + hoverState * 20, this.width / 2, this.height);
        this.drawCenteredString(font, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, color);
    }
    
}
