package com.suchaz.app.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.suchaz.app.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.suchaz.app.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.SuchAzUser.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.SuchAzUser.class.getName() + ".userGiftWrappers", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.SuchAzUser.class.getName() + ".consumerProfiles", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.SuchAzUser.class.getName() + ".traits", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.UserProfile.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Trait.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Trait.class.getName() + ".traitImages", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Trait.class.getName() + ".suchAzUsers", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Hobby.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Hobby.class.getName() + ".hobbyImages", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Relationship.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Relationship.class.getName() + ".relationshipImages", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Occassion.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Occassion.class.getName() + ".occasionImages", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Store.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Store.class.getName() + ".storeImages", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Store.class.getName() + ".items", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Category.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Category.class.getName() + ".items", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Category.class.getName() + ".children", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Category.class.getName() + ".itemAttributeTypes", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Category.class.getName() + ".categoryImages", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.GiftWrapper.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.GiftWrapper.class.getName() + ".userGiftWrappers", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Vendor.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Vendor.class.getName() + ".items", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Vendor.class.getName() + ".vendorImages", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.VendorImage.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.StoreImage.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.UserGiftWrapper.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.CategoryImage.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.RelationshipImage.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.OccasionImage.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.HobbyImage.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.TraitImage.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.ItemImage.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Country.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Country.class.getName() + ".vendors", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.ConsumerProfile.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Item.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Item.class.getName() + ".wishListItems", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Item.class.getName() + ".buyLaterListItems", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Item.class.getName() + ".activityListItems", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Item.class.getName() + ".itemCommonAttributes", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Item.class.getName() + ".itemReviews", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Item.class.getName() + ".userGiftWrappers", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Item.class.getName() + ".itemImages", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Item.class.getName() + ".offers", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Item.class.getName() + ".stores", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.ItemReview.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Offer.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Offer.class.getName() + ".items", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.ItemCommonAttribute.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.ItemAttributeType.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.ItemAttributeType.class.getName() + ".itemCommonAttributes", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.WishList.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.WishList.class.getName() + ".wishListItems", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.WishListItem.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.BuyLaterList.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.BuyLaterList.class.getName() + ".buyLaterListItems", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.BuyLaterListItem.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.ActivityList.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.ActivityList.class.getName() + ".activityListItems", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.ActivityListItem.class.getName(), jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.Category.class.getName() + ".itemCommonAttributes", jcacheConfiguration);
            cm.createCache(com.suchaz.app.domain.ConsumerProfileHistory.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
