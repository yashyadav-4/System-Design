package LLD.SOLID;

interface RecommendationStrategy{
    void recommend();
}

class TrendingRecommendation implements RecommendationStrategy{
    public void recommend(){

    }
}

class GenreRecommendation implements RecommendationStrategy{
    public void recommend(){

    }
}

class RecentRecommendation implements RecommendationStrategy{
    public void recommend(){

    }
}

// high level
class RecommendationAlgorithm{
    private RecommendationStrategy recommendationStrategy;
    public RecommendationAlgorithm(RecommendationStrategy recommendationStrategy){
        this.recommendationStrategy = recommendationStrategy;
    }

    public void recommend(){
        recommendationStrategy.recommend();
    }

}

public class DependencyInversionPrinciple {
    public static void main(String[] args) {
        RecommendationAlgorithm recommendationAlgorithm = new RecommendationAlgorithm(new TrendingRecommendation());
        recommendationAlgorithm.recommend();
    }
}


/*
High-level modules should not depend on low-level modules.
Both should depend on abstractions.
Abstractions should not depend on details.
Details should depend on abstractions.

*/