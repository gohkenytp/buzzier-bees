package com.bagel.buzzierbees.common.effects;

import com.google.common.collect.ImmutableList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.*;
import java.util.Iterator;

public class AntiEffect extends InstantEffect {
    private final ImmutableList<EffectInstance> counteredEffects;

    protected AntiEffect(EffectType typeIn, int liquidColorIn, EffectInstance... counteredEffectsIn) {
        super(typeIn, liquidColorIn);
        counteredEffects = ImmutableList.copyOf(counteredEffectsIn);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        Iterator entityEffects = entityLivingBaseIn.getActivePotionEffects().iterator();
        while (entityEffects.hasNext()) {
            EffectInstance entityEffect = (EffectInstance) entityEffects.next();
			Iterator effectsIterator = counteredEffects.iterator();
            while(effectsIterator.hasNext()) {
                EffectInstance counteredEffectInstance = (EffectInstance) effectsIterator.next();
                Effect counteredEffect = counteredEffectInstance.getPotion();
                if (entityEffect.getPotion() == counteredEffect) {
                    entityLivingBaseIn.removePotionEffect(counteredEffect);
                }
            }
        }
    }
}