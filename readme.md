###benefits of thread pool
* We don't have to manually create, manage, schedule or terminate our threads. The thread pool does this for us.
* If we used Fixed size thread pool, we don't have to worry about having too many threads which might affect system performance.
###returning values from threads
* We can submit Callable<V> interface with generic return type (V) in Thread Pool executor which gives us a Future<V> object.
* The Future object has .get() method that waits for end of computation inside callable and return a value of generic type V.
###using callable
* it's an interface so we can either create a class and implement it, or we can create an anonymous class out of it or lambda expression.
* In any way we can specify the return type by using the generic type parameter V in Callable interface.