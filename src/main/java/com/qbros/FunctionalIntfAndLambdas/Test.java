
public class Main {

    public static void main(String[] args) throws InterruptedException {
        //with just we can create a publisher from an array of values.
        Flowable.just(3, 5, -2, 9, 11, 12, 13, 3, 5, -2)
                .take(4)
                .subscribeOn(Schedulers.newThread())
                .subscribe(tick -> System.out.println("tick value" + tick+"on thread "+Thread.currentThread().getName()),
                        (ex) -> System.out.println("got Exception" + ex),
                        () -> System.out.println("completed"));


        //with just we can create a publisher from an array of values.
        Flowable flowable = Flowable.just("3", 5, -2, 9, 11, 12, 13, 3, 5, -2);
        flowable.observeOn(Schedulers.newThread())
                .subscribe(tick -> System.out.println("tick value" + tick + "on thread " + Thread.currentThread().getName()),
                        (ex) -> System.out.println("got Exception" + ex),
                        () -> System.out.println("completed"));

        flowable
                .subscribe(tick -> System.out.println("-----" + tick + "on thread " + Thread.currentThread().getName()),
                        (ex) -> System.out.println("got Exception" + ex),
                        () -> System.out.println("completed"));

        //we can define our subscribers inline (lambdas) or in a different class
        Flowable.fromPublisher(new PublisherTest()).subscribe(
                tick -> System.out.println("tick value"+tick),
                (ex) -> System.out.println("got Exception" + ex),
                () -> System.out.println("completed"));

        //use same followable
        Flowable<String> flowable = Flowable.fromPublisher(new PublisherTest())
                .observeOn(Schedulers.newThread());
        flowable.subscribe(new SubscriberTest());

        //subscribeOn vs observe on
        //https://medium.com/@mgn524/rx-java-subscribeon-and-observeon-a7d95041ce96

        //stop the main thread to se the result on subscribers (which are running on different threads)
        Thread.sleep(5000);
    }
}

//Threading in RxJava is done with help of Schedulers. Scheduler can be thought of as a thread pool managing 1 or more threads.
//Whenever a Scheduler needs to execute a task, it will take a thread from its pool and run the task in that thread.

class SubscriberTest implements Subscriber<String> {

    @Override
    public void onSubscribe(Subscription subscription) {
    }

    //By default subscriber's onNext will run on the same thread you call the .subscribe.
    @Override
    public void onNext(String s) {
        System.out.println("Got Value" + s);
        System.out.println("Subscriber thread" + Thread.currentThread());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("err");
    }

    @Override
    public void onComplete() {
        System.out.println("completed");
    }
}

class PublisherTest implements Publisher<String> {

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        /*
         * to push data into each subscriber we get the subscriber and put the data in it(using inNext())
         * this process repeats for each subscriber.
         * */
        try {
            subscriber.onNext("a from " + Thread.currentThread().getName());
            Thread.sleep(1000);
            subscriber.onNext("b from " + Thread.currentThread().getName());
            Thread.sleep(1000);
            subscriber.onNext("c from " + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ObservableTest extends Observable<String> {
    @Override
    protected void subscribeActual(Observer<? super String> observer) {
    }
}
