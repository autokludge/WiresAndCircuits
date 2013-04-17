package com.dmillerw.wac.core.options;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class OptionSelection extends Option {

	public Selection[] data;
	
	public OptionSelection() {}
	
	public OptionSelection(Selection[] data) {
		this.data = data;
	}
	
	@Override
	public OptionType getType() {
		return OptionType.SELECTION;
	}

	@Override
	public Option readFromNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("data")) {
			NBTTagList data = nbt.getTagList("data");
			Selection[] dataArray = new Selection[data.tagCount()];
			
			for (int i=0; i<data.tagCount(); i++) {
				Selection selection = new Selection();
				selection.readFromNBT((NBTTagCompound) data.tagAt(i));
				dataArray[i] = selection;
			}
			
			this.data = dataArray;
		}
		return this;
	}

	@Override
	public Option writeToNBT(NBTTagCompound nbt) {
		NBTTagList dataList = new NBTTagList();

		for (int i=0; i<data.length; i++) {
			NBTTagCompound selection = new NBTTagCompound();
			data[i].writeToNBT(selection);
			dataList.appendTag(selection);
		}
		
		nbt.setTag("data", dataList);
		return this;
	}
	
}
