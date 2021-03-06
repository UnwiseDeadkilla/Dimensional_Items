package com.unwisedeadkilla.dimensional_items.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.unwisedeadkilla.dimensional_items.Dimensional_Items;

public class QuartzLamp extends Block{

	private final boolean isOn;
	
	protected QuartzLamp(boolean isOn) {
		super(Material.redstoneLight);
		
		this.isOn = isOn;
		this.setStepSound(soundTypeGlass);
		this.setResistance(100F);
		this.setHardness(3F);
		this.setHarvestLevel("pickaxe", 0);
		
		if(isOn){
			this.setLightLevel(1F);
		}
	}
	
	public void registerBlockIcons(IIconRegister ir){
		this.blockIcon = ir.registerIcon(Dimensional_Items.modID + ":" + (this.isOn ? "QuartzLamp" : "LampOff"));
	}
	
	public void onBlockAdded(World world, int x, int y, int z){
		
		if(!world.isRemote){
			if(this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z)){
				world.scheduleBlockUpdate(x, y, z, this, 4);
			}
			
			else if(!this.isOn && world.isBlockIndirectlyGettingPowered(x, y, z)){
				world.setBlock(x, y, z, DIBlocks.blockQuartzLampOn, 0, 2);
			}
		}
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block){
		
		if(!world.isRemote){
			if(this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z)){
				world.scheduleBlockUpdate(x, y, z, this, 4);
			}
			
			else if(!this.isOn && world.isBlockIndirectlyGettingPowered(x, y, z)){
				world.setBlock(x, y, z, DIBlocks.blockQuartzLampOn, 0, 2);
			}
		}
	}
	
	public void updateTick(World world, int x, int y, int z, Random random){
		
		if(!world.isRemote && this.isOn && !world.isBlockIndirectlyGettingPowered(x, y, z)){
			world.setBlock(x, y, z, DIBlocks.blockQuartzLampOff, 0, 2);
		}
	}
	
	public Item getItemDropped(int meta, Random random, int Fortune){
		return Item.getItemFromBlock(DIBlocks.blockQuartzLampOff);
	}
	
	protected ItemStack createStackedBlock(int i){
		return new ItemStack(DIBlocks.blockQuartzLampOff);
	}

}
