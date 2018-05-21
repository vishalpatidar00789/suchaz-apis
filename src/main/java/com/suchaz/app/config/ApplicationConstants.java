package com.suchaz.app.config;

/**
 * Application constants.
 */
public final class ApplicationConstants {

    // Regex for acceptable logins
    public static final String TRAIT = "trait";
    public static final String HOBBY = "hobby";
    public static final String RELATIONSHIP = "relatioship";
    public static final String OCCASSION = "occassion";
    public static final String STORE = "store";
    public static final String CATEGORY = "category";
    public static final String FEATURED_PRODUCTS = "featuredProducts";
    public static final String GENDER_TAG = "Gender";
    public static final String AGE_GROUP_TAG = "AgeGroup";
    public static final String WEEKLY_FEATURED_PRODUTS = "weeklyFeaturedProducts";
    public static final String SUCHAZ_MENU_LIST = "suchAzMenuList";
    
    public static enum GENDER{
    	MALE("Male"),FEMALE("Female");
    	private String gender;
    	GENDER(String gender)
    	{
    		this.gender = gender;
    	}
    public String getValue()
    {
    	return gender;
    }
    }
    
    public static enum AGE_GROUP{
    	KIDS("Kids"),TEEN("Teen"),YOUTH("Youth"),ELDERS("Elders");
    	private String ageGroup;
    	AGE_GROUP(String ageGroup)
    	{
    		this.ageGroup = ageGroup;
    	}
    public String getValue()
    {
    	return ageGroup;
    }
    }
    
    private ApplicationConstants() {
    }
}
