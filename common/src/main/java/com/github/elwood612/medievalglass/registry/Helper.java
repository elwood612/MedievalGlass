package com.github.elwood612.medievalglass.registry;

public class Helper {
    private static final int[] OPTIFINE_CTM_LOOKUP = new int[256];
    static {
        OPTIFINE_CTM_LOOKUP[0] =    0;
        OPTIFINE_CTM_LOOKUP[4] =    1;
        OPTIFINE_CTM_LOOKUP[68] =   2;
        OPTIFINE_CTM_LOOKUP[64] =   3;
        OPTIFINE_CTM_LOOKUP[20] =   4;
        OPTIFINE_CTM_LOOKUP[80] =   5;
        OPTIFINE_CTM_LOOKUP[21] =   6;
        OPTIFINE_CTM_LOOKUP[84] =   7;
        OPTIFINE_CTM_LOOKUP[87] =   8;
        OPTIFINE_CTM_LOOKUP[93] =   9;
        OPTIFINE_CTM_LOOKUP[245] =  10;
        OPTIFINE_CTM_LOOKUP[215] =  11;
        OPTIFINE_CTM_LOOKUP[16] =   12;
        OPTIFINE_CTM_LOOKUP[28] =   13;
        OPTIFINE_CTM_LOOKUP[124] =  14;
        OPTIFINE_CTM_LOOKUP[112] =  15;
        OPTIFINE_CTM_LOOKUP[5] =    16;
        OPTIFINE_CTM_LOOKUP[65] =   17;
        OPTIFINE_CTM_LOOKUP[69] =   18;
        OPTIFINE_CTM_LOOKUP[81] =   19;
        OPTIFINE_CTM_LOOKUP[213] =  20;
        OPTIFINE_CTM_LOOKUP[117] =  21;
        OPTIFINE_CTM_LOOKUP[125] =  22;
        OPTIFINE_CTM_LOOKUP[95] =   23;
        OPTIFINE_CTM_LOOKUP[17] =   24;
        OPTIFINE_CTM_LOOKUP[31] =   25;
        OPTIFINE_CTM_LOOKUP[255] =  26;
        OPTIFINE_CTM_LOOKUP[241] =  27;
        OPTIFINE_CTM_LOOKUP[29] =   28;
        OPTIFINE_CTM_LOOKUP[116] =  29;
        OPTIFINE_CTM_LOOKUP[23] =   30;
        OPTIFINE_CTM_LOOKUP[92] =   31;
        OPTIFINE_CTM_LOOKUP[247] =  32;
        OPTIFINE_CTM_LOOKUP[223] =  33;
        OPTIFINE_CTM_LOOKUP[119] =  34;
        OPTIFINE_CTM_LOOKUP[221] =  35;
        OPTIFINE_CTM_LOOKUP[1] =    36;
        OPTIFINE_CTM_LOOKUP[7] =    37;
        OPTIFINE_CTM_LOOKUP[199] =  38;
        OPTIFINE_CTM_LOOKUP[193] =  39;
        OPTIFINE_CTM_LOOKUP[71] =   40;
        OPTIFINE_CTM_LOOKUP[209] =  41;
        OPTIFINE_CTM_LOOKUP[197] =  42;
        OPTIFINE_CTM_LOOKUP[113] =  43;
        OPTIFINE_CTM_LOOKUP[253] =  44;
        OPTIFINE_CTM_LOOKUP[127] =  45;
        OPTIFINE_CTM_LOOKUP[85] =   46;
    }

    public static int getVerticalPosition(boolean up, boolean down) {
        // 0 = isolated
        // 1 = top (has bottom only)
        // 2 = middle (both)
        // 3 = bottom (has top only)
        if (up) {
            if (down) return 2;
            else return 3;
        } else {
            if (down) return 1;
            else return 0;
        }
    }

    public static int getEightwayPosition(boolean top, boolean topRight, boolean right, boolean bottomRight,
                                          boolean bottom, boolean bottomLeft, boolean left, boolean topLeft) {
        // top          = 1
        // topRight     = 2
        // right        = 4
        // bottomRight  = 8
        // bottom       = 16
        // bottomLeft   = 32
        // left         = 64
        // topLeft      = 128

        int mask = 0;

        if (top)                            mask |= 1 << 0;
        if (right)                          mask |= 1 << 2;
        if (bottom)                         mask |= 1 << 4;
        if (left)                           mask |= 1 << 6;
        if (top && right && topRight)       mask |= 1 << 1;
        if (right && bottom && bottomRight) mask |= 1 << 3;
        if (bottom && left && bottomLeft)   mask |= 1 << 5;
        if (left && top && topLeft)         mask |= 1 << 7;

        return OPTIFINE_CTM_LOOKUP[mask];
    }
}


