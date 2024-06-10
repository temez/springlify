package dev.temez.springlify.platform.configuration.bukkit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Configuration class representing the attribute modifier properties.
 *
 * <p>This class is used for configuring attribute modifier properties,
 * such as the attribute itself, uuid, name, amount, operation, and slot.</p>
 *
 * @since 0.7.0.0-RC2
 */
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttributeModifierConfiguration {

    /**
     * The attribute.
     */
    @NotNull
    Attribute attribute;

    /**
     * Unique identifier of the attribute modifier.
     */
    @NotNull
    UUID uuid = UUID.randomUUID();

    /**
     * Attribute modifier custom-name.
     */
    @NotNull
    String name;

    /**
     * Amount of modifier for adding/subtracting etc.
     */
    int amount;

    /**
     * The operation that will be applied to the attribute.
     * For example: add/subtract a value.
     */
    @NotNull
    AttributeModifier.Operation operation;

    /**
     * Where the attribute modifier will be activated.
     * For example: in the left hand, in the right hand.
     * (Optional)
     */
    @Nullable
    EquipmentSlot slot;

}