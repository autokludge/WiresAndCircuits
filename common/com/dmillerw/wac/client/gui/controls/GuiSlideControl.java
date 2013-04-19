package com.dmillerw.wac.client.gui.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSlideControl extends GuiSpecialButton {
	
    public boolean dragging = false;
    public boolean drawBackground = false;
    
    public float slideValue;
    public float minValue;
    public float maxValue;
    
    public GuiSlideControl(int id, int x, int y, int width, float initialValue, float minValue, float maxValue) {
    	this(id, x, y, width, 20, initialValue, minValue, maxValue);
    }
    
    public GuiSlideControl(int id, int x, int y, int width, int height, float initialValue, float minValue, float maxValue) {
        super(id, x, y, width, height, "");
        
        this.slideValue = ((initialValue - minValue) / (maxValue - minValue));
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public GuiSlideControl setDrawBackground(boolean value) {
    	this.drawBackground = value;
    	return this;
    }
    
    @Override
    public int getHoverState(boolean par1) {
        return 0;
    }

    protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {
        if (this.drawButton) {
            if (this.dragging) {
            	slide(par2);
            }
            applyScrollLimits();
            draw();
        }
    }

    public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {
        if (super.mousePressed(par1Minecraft, par2, par3)) {
        	slide(par2);
	        applyScrollLimits();
            this.dragging = true;
            return true;
        } else {
            return false;
        }
    }

    public void applyScrollLimits() {
    	if (this.slideValue < 0.0F) {
            this.slideValue = 0.0F;
        }

        if (this.slideValue > 1.0F) {
            this.slideValue = 1.0F;
        }
    }
    
    public void draw() {
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(this.xPosition + (int)(this.slideValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
        this.drawTexturedModalRect(this.xPosition + (int)(this.slideValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
    }
    
    public void slide(int amount) {
    	float slideAmount = (float)(amount - (this.xPosition + 4)) / (float)(this.width - 8);
    	
    	if (amount == -1) {
    		this.slideValue += slideAmount;
    	} else if (amount == 1) {
    		this.slideValue -= slideAmount;
    	} else {
    		this.slideValue = slideAmount;
    	}
    }
    
    @Override
    public void drawBackgroundAndText(FontRenderer font, int hoverState, int color) {
    	if (drawBackground) {
    		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/gui/gui.png");
    		this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + hoverState * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + hoverState * 20, this.width / 2, this.height);
    	}
    }
    
	public float getShiftedValue() {
		return (this.maxValue - this.minValue) * this.slideValue + this.minValue;
	}
    
    public void mouseReleased(int par1, int par2) {
        this.dragging = false;
    }
}
