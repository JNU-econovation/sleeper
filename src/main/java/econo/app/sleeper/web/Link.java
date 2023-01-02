package econo.app.sleeper.web;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Builder
public class Link {

    private final String rel;
    private final String href;
    private final  String action;
    private final List<String> types;

    public static Link of(String rel,String href, String action, List<String> types){
        return Link.builder()
                .rel(rel)
                .href(href)
                .action(action)
                .types(types)
                .build();
    }

}
