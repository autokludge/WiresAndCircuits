package com.dmillerw.wac.client.gui.controls;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

import com.dmillerw.wac.lib.ModInfo;

import cpw.mods.fml.client.FMLClientHandler;

public class GuiVerticalSlideControl extends GuiSlideControl {

	//TODO Make sure scrolling (and slider) values limit themselves based on element mapping length
	public GuiVerticalSlideControl(int id, int x, int y, int height, float initialValue, float minValue, float maxValue) {
		super(id, x, y, 20, height, initialValue, minValue, maxValue);
	}

	protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {
        if (this.drawButton) {
            if (this.dragging) {
            	slide(par3);
            }
            applyScrollLimits();
            draw();
        }
    }

    public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {
        if (super.mousePressed(par1Minecraft, par2, par3)) {
        	slide(par3);
            applyScrollLimits();
            
            this.dragging = true;
            return true;
        } else {
            return false;
        }
    }
	
    @Override
    public void slide(int amount) {
    	float slideAmount = (float) (((amount - (this.yPosition + 4)) / (float) (this.height / 2)) / 8);
    	
    	if (amount == -1) {
    		this.slideValue += slideAmount;
    	} else if (amount == 1) {
    		this.slideValue -= slideAmount;
    	} else {
    		this.slideValue = slideAmount;
    	}
    }
    
	@Override
	public void draw() {
		FMLClientHandler.instance().getClient().renderEngine.bindTexture("/mods/"+ModInfo.MOD_ID.toLowerCase()+"/textures/gui/verticalControls.png");
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(this.xPosition, this.yPosition + (int)(this.slideValue * (float)(this.height - 8)),     0, 0, 12, 4);
        this.drawTexturedModalRect(this.xPosition, this.yPosition + (int)(this.slideValue * (float)(this.height - 8)) + 4, 0, 4, 12, 4);
    }

}
