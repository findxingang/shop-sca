// package pers.xingang.shop.gateway.predicate;
//
// import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
// import org.springframework.stereotype.Component;
// import org.springframework.util.StringUtils;
// import org.springframework.web.server.ServerWebExchange;
//
// import java.util.Arrays;
// import java.util.List;
// import java.util.function.Predicate;
//
// /**
//  * @author xingang
//  * @since 2024/04/24 19:02
//  */
// @Component
// public class NameRoutePredicateFactory extends AbstractRoutePredicateFactory<NameRoutePredicateConfig> {
//
//     public NameRoutePredicateFactory() {
//         super(NameRoutePredicateConfig.class);
//     }
//
//     @Override
//     public Predicate<ServerWebExchange> apply(NameRoutePredicateConfig config) {
//         return (serverWebExchange)->{
//             String name = serverWebExchange.getRequest().getQueryParams().getFirst("name");
//             if (StringUtils.isEmpty(name)){
//                 name = "";
//             }
//             return name.equals(config.getName());
//         };
//     }
//
//     @Override
//     public List<String> shortcutFieldOrder() {
//         return Arrays.asList("name");
//     }
// }
