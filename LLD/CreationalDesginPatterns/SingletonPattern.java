package LLD.CreationalDesginPatterns;


// Eager Loading --> its thread Safe but memory inefficient
class CounterEager{
    private static final CounterEager counter = new CounterEager();

    private CounterEager(){
    }

    public static CounterEager getInstance(){
        return counter;
    }
}



// Lazy Loading --> not thread safe
class CounterLazy{
    private static CounterLazy counter;

    private CounterLazy(){
    }

    public static CounterLazy getInstance(){
        if(counter == null){
            counter = new CounterLazy();
        }
        return counter;
    }

}



// 1. thread safe lazy loading with synchronized keyword
class CounterLazySync{
    private static CounterLazySync counter;

    private CounterLazySync(){
    }

    public static synchronized CounterLazySync getInstance(){
        if(counter == null){
            counter = new CounterLazySync();
        }
        return counter;
    }

}



// 2. thread safe lazy loading with double-checked locking using synchronized and volatile keyword
class CounterLazyDoubleCheckedLocking{
    private static volatile CounterLazyDoubleCheckedLocking counter;

    private CounterLazyDoubleCheckedLocking(){
    }

    public static CounterLazyDoubleCheckedLocking getInstance(){
        if(counter == null){
            synchronized (CounterLazyDoubleCheckedLocking.class){
                if(counter == null){
                    counter = new CounterLazyDoubleCheckedLocking();
                }
            }
        }
        return counter;
    }

}



// 3. Bill perg singleton for java 5+ (inner class eager loads but as inner class is not directly loaded to main memory until outer class ask it acts as a lazy loading for outer class)
class CounterLazyLoadingBillPerg{

    private CounterLazyLoadingBillPerg(){
    }

    private static class holder{
        private static final CounterLazyLoadingBillPerg counter = new CounterLazyLoadingBillPerg();
    }

    public static CounterLazyLoadingBillPerg getInstance(){
        return holder.counter;
    }
}


public class SingletonPattern {
    public static void main(String[] args) {
        // Eager loading
        CounterEager counter1 = CounterEager.getInstance();
        CounterEager counter2 = CounterEager.getInstance();

        System.out.println(counter1);
        System.out.println(counter2);


        // Lazy loading

        CounterLazy counter3 = CounterLazy.getInstance();
        CounterLazy counter4 = CounterLazy.getInstance();

        System.out.println(counter1);
        System.out.println(counter2);
    }
}
