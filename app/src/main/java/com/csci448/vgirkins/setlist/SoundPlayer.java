package com.csci448.vgirkins.setlist;
// https://www.youraccompanist.com/free-scales-and-warm-ups/reference-scales#majorminor

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.HashMap;

public class SoundPlayer {
    private static SoundPool mSoundPool;
    private static HashMap mSoundPoolMap;

    public static final int A = R.raw.a;
    public static final int Bf = R.raw.bf;
    public static final int B = R.raw.b;
    public static final int C = R.raw.c;
    public static final int Df = R.raw.df;
    public static final int D = R.raw.d;
    public static final int Ef = R.raw.ef;
    public static final int E = R.raw.e;
    public static final int F = R.raw.f;
    public static final int Gf = R.raw.gf;
    public static final int G = R.raw.g;
    public static final int Af = R.raw.af;

    public static void initSounds(Context context) {
        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 100);
        mSoundPoolMap = new HashMap(12);

        mSoundPoolMap.put(A, mSoundPool.load(context, R.raw.a, 1));
        mSoundPoolMap.put(Bf, mSoundPool.load(context, R.raw.bf, 1));
        mSoundPoolMap.put(B, mSoundPool.load(context, R.raw.b, 1));
        mSoundPoolMap.put(C, mSoundPool.load(context, R.raw.c, 1));
        mSoundPoolMap.put(Df, mSoundPool.load(context, R.raw.df, 1));
        mSoundPoolMap.put(D, mSoundPool.load(context, R.raw.d, 1));
        mSoundPoolMap.put(Ef, mSoundPool.load(context, R.raw.ef, 1));
        mSoundPoolMap.put(E, mSoundPool.load(context, R.raw.e, 1));
        mSoundPoolMap.put(F, mSoundPool.load(context, R.raw.f, 1));
        mSoundPoolMap.put(Gf, mSoundPool.load(context, R.raw.gf, 1));
        mSoundPoolMap.put(G, mSoundPool.load(context, R.raw.g, 1));
        mSoundPoolMap.put(Af, mSoundPool.load(context, R.raw.af, 1));
    }

    public static void playSound(Context context, int soundId) {
        if (mSoundPool == null || mSoundPoolMap == null) {
            initSounds(context);
        }

        float volume = 0.5f;

        mSoundPool.play((int)mSoundPoolMap.get(soundId), volume, volume, 1, 0, 1f);
    }
}