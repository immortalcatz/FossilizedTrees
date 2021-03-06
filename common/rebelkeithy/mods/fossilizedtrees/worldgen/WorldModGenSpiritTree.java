// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package rebelkeithy.mods.fossilizedtrees.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;
import rebelkeithy.mods.fossilizedtrees.FossilizedTreesBlocks;
import cpw.mods.fml.common.IWorldGenerator;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block, BlockLeaves, 
//            BlockGrass

public class WorldModGenSpiritTree extends WorldGenerator implements IWorldGenerator
{
	int treeMinHeight = 7;
	int treeMaxHeight = 20;
	
    public WorldModGenSpiritTree()
    {
    }

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		int veinCount = 1;
		int maxHeight = 55;
		int minHeight = 5;
		
		for(int n = 0; n < veinCount; n++)
		{
			int randPosX = chunkX*16 + random.nextInt(16);
			int randPosY = random.nextInt(maxHeight - minHeight) + minHeight;
			int randPosZ = chunkZ*16 + random.nextInt(16);
			
			generate(world, random, randPosX, randPosY, randPosZ);
		}
	}

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
    	System.out.println("gen " + par3 + " " + par4 + " " + par5);
        int l = par2Random.nextInt(treeMaxHeight - treeMinHeight) + treeMinHeight;
        boolean flag = true;

        if (par4 >= 1 && par4 + l + 1 <= 256)
        {
            int i1;
            byte b0;
            int j1;
            int k1;

            for (i1 = par4; i1 <= par4 + 1 + l; ++i1)
            {
                b0 = 1;

                if (i1 == par4)
                {
                    b0 = 0;
                }

                if (i1 >= par4 + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (int l1 = par3 - b0; l1 <= par3 + b0 && flag; ++l1)
                {
                    for (j1 = par5 - b0; j1 <= par5 + b0 && flag; ++j1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            k1 = par1World.getBlockId(l1, i1, j1);

                            Block block = Block.blocksList[k1];

                            if (k1 != 0 &&
                               !block.isLeaves(par1World, l1, i1, j1) &&
                                k1 != Block.grass.blockID &&
                                k1 != Block.dirt.blockID &&
                               !block.isWood(par1World, l1, i1, j1))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            /*
            if (!flag)
            {
                return false;
            }
            else
            */
            {
                i1 = par1World.getBlockId(par3, par4 - 1, par5);
                Block soil = Block.blocksList[i1];
                boolean isSoil = (soil != null && soil.canSustainPlant(par1World, par3, par4 - 1, par5, ForgeDirection.UP, (BlockSapling)Block.sapling));

                isSoil = true;
                if (isSoil && par4 < 256 - l - 1)
                {
                    //soil.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
                    b0 = 3;
                    byte b1 = 0;
                    int i2;
                    int j2;
                    int k2;

                    for (j1 = par4 - b0 + l; j1 <= par4 + l; ++j1)
                    {
                        k1 = j1 - (par4 + l);
                        i2 = b1 + 1 - k1 / 2;

                        for (j2 = par3 - i2; j2 <= par3 + i2; ++j2)
                        {
                            k2 = j2 - par3;

                            for (int l2 = par5 - i2; l2 <= par5 + i2; ++l2)
                            {
                                int i3 = l2 - par5;

                                if (Math.abs(k2) != i2 || Math.abs(i3) != i2 || par2Random.nextInt(2) != 0 && k1 != 0)
                                {
                                    int j3 = par1World.getBlockId(j2, j1, l2);
                                    Block block = Block.blocksList[j3];

                                    //if (block == null || block.canBeReplacedByLeaves(par1World, j2, j1, l2))
                                    {
                                        this.setBlockAndMetadata(par1World, j2, j1, l2, FossilizedTreesBlocks.fossilizedLeaves.blockID, 0);
                                    }
                                }
                            }
                        }
                    }

                    for (j1 = 0; j1 < l; ++j1)
                    {
                        k1 = par1World.getBlockId(par3, par4 + j1, par5);

                        Block block = Block.blocksList[k1];

                        //if (k1 == 0 || block == null || block.isLeaves(par1World, par3, par4 + j1, par5))
                        {
                            this.setBlockAndMetadata(par1World, par3, par4 + j1, par5, FossilizedTreesBlocks.fossilizedLog.blockID, 0);
                            //((TileEntityNamed)par1World.getBlockTileEntity(par3, par4 + j1, par5)).setName(name);

                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
}
