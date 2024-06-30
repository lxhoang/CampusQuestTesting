package api.endpoints;

public class Routes {
    public static String base_quest_url = "https://api.quest.opencampus.xyz";
    public static String base_referral_url = "https://api.referral.opencampus.xyz";

    // X(Twitter) module
    public static String twitter_profile_get_url = base_quest_url + "/twitter/profile";

    // api referral module
    public static String api_referral_url = base_referral_url + "/points/balance";

    // completed quests module
    public static String completed_quest_url = base_quest_url + "/quests/records/completed";

    // active quests module
    public static String active_quest_url = base_quest_url + "/quests/active";

}
