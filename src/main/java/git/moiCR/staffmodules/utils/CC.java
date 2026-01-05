package git.moiCR.staffmodules.utils;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class CC {

    private final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder()
            .character('&')
            .hexCharacter('#')
            .build();

    private final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public Component translate(String toTranslate) {
        if (toTranslate == null) return Component.empty();
        Component legacyComponent = LEGACY_SERIALIZER.deserialize(toTranslate);
        String miniMessageText = MINI_MESSAGE.serialize(legacyComponent);
        return MINI_MESSAGE.deserialize(miniMessageText);
    }

    public List<Component> translate(List<String> toTranslate) {
        return toTranslate.stream()
                .map(CC::translate)
                .collect(Collectors.toList());
    }

    public List<Component> translate(String... text) {
        return translate(Arrays.asList(text));
    }
}
