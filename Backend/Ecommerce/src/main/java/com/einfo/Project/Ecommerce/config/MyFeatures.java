//package com.einfo.Project.Ecommerce.config;
//
//import org.springframework.context.annotation.Bean;
//import org.togglz.core.Feature;
//import org.togglz.core.annotation.EnabledByDefault;
//import org.togglz.core.annotation.Label;
//import org.togglz.core.manager.EnumBasedFeatureProvider;
//import org.togglz.core.spi.FeatureProvider;
//
//public enum MyFeatures implements Feature {
//    @EnabledByDefault
//    @Label("DISCOUNT_APPLIED")
//    DISCOUNT_APPLIED;
//
//
//	@Bean
//	public FeatureProvider featureProvider() {
//	    return new EnumBasedFeatureProvider(MyFeatures.class);
//	}
//}
