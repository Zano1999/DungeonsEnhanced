package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.legacy.structure_gel.api.structure.GelConfigJigsawStructure;
import com.legacy.structure_gel.api.structure.jigsaw.AbstractGelStructurePiece;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawPoolBuilder;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawRegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Random;

public class DELargeDungeon extends GelConfigJigsawStructure<JigsawConfiguration> {
    public DELargeDungeon() {
        super(JigsawConfiguration.CODEC, DEConfig.COMMON.large_dungeon, -16, true, true, (context) -> checkLocation(context, DETerrainAnalyzer.defaultCheckSettings));
        Pool.init();
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return true;}

    private static boolean checkLocation(PieceGeneratorSupplier.Context<JigsawConfiguration> context, DETerrainAnalyzer.Settings checkSettings){
        if(context.validBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG)){
            return DETerrainAnalyzer.isPositionSuitable(context.chunkPos(), context.chunkGenerator(), DETerrainAnalyzer.GenerationType.onGround, checkSettings, context.heightAccessor());
        }

        return false;
    }

    public static class Piece extends AbstractGelStructurePiece {
        public Piece(StructureManager structureManager, StructurePoolElement poolElement, BlockPos pos, int groundLevelDelta, Rotation rotation, BoundingBox bounds) {
            super(structureManager, poolElement, pos, groundLevelDelta, rotation, bounds);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {super(serializationContext, nbt);}

        @Override
        public StructurePieceType getType() {return DEStructures.LargeDungeon.getPieceType();}
        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, Random random, BoundingBox box) {}
    }

    public static class Pool{
        public static Holder<StructureTemplatePool> Root;
        public static void init(){}
        static{
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.ModID, "large_dungeon/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false).processors(DEUtil.Processors.AirToCobweb);
            Root = registry.register("root", poolBuilder.clone().names("root").build());

            JigsawPoolBuilder Cross = poolBuilder.clone().names("cross");
            JigsawPoolBuilder Rooms = poolBuilder.clone().names("room_small1", "room_small2", "room1", "room2", "room_big", "parkour", "storage");
            JigsawPoolBuilder Tunnels = poolBuilder.clone().names("tunnel");
            JigsawPoolBuilder Stairs = poolBuilder.clone().names("stairs");

            registry.register("cross", Cross.build());
            registry.register("main", JigsawPoolBuilder.collect(Tunnels.weight(4), Stairs.weight(2), Cross.weight(2), Rooms.weight(1)));
        }
    }
}