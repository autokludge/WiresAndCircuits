package com.dmillerw.wac.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import cpw.mods.fml.common.IWorldGenerator;

public class OreGenerator implements IWorldGenerator {

	private int blockID;
	private int blockMeta;
	private int blockAmount;
	private int yMax;
	
	private int replaceBlock;
	
	public OreGenerator(int blockID, int blockMeta, int blockAmount, int yMax) {
		this.blockID = blockID;
		this.blockMeta = blockMeta;
		this.blockAmount = blockAmount;
		this.yMax = yMax;
		this.replaceBlock = Block.stone.blockID;
	}
	
	public OreGenerator(int blockID, int blockMeta, int blockAmount, int yMax, int replaceBlock) {
		this(blockID, blockMeta, blockAmount, yMax);
		this.replaceBlock = replaceBlock;
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch(world.provider.dimensionId){
        case -1:
            generateNether(world, random, chunkX * 16, chunkZ * 16);
            break;
        case 0:
            generateSurface(world, random, chunkX * 16, chunkZ * 16);
            break;
        case 1:
            generateEnd(world, random, chunkX * 16, chunkZ * 16);
            break;
        }
	}

	private void generateEnd(World world, Random rand, int chunkX, int chunkZ) {}

	private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
        for(int k = 0; k < 10; k++){
        	int firstBlockXCoord = chunkX + rand.nextInt(16);
        	int firstBlockYCoord = rand.nextInt(yMax);
        	int firstBlockZCoord = chunkZ + rand.nextInt(16);
        	
        	(new WorldGenMinable(blockID, blockMeta, blockAmount, replaceBlock)).generate(world, rand, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
        }
	}

	private void generateNether(World world, Random rand, int chunkX, int chunkZ) {}
	
}
