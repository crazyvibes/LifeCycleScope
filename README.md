# LifeCycleScope

-We learnt how to easily use this scope from an extension function available with viewmodel-ktx
 library 
-instead of creating new scopes and handling cancellations manually. With the lifecycle-runtime-ktx
 library , google introduced another very useful scope named lifecycle scope. A LifecycleScope is defined
 for each Lifecycle object. Any coroutine launched in this scope is canceled when the Lifecycle is destroyed.

-You can access the CoroutineScope of the Lifecycle either via lifecycle.coroutineScope
 or lifecycleOwner.lifecycleScope properties.

-Sometimes we need to create corutines in objects with a lifecycle, such as activites and fragments.

-This new scope has specially created for those scenarios.

-All the coroutines in this new scope will be canceled when the Lifecycle is destroyed.

-So if it is in an activity , all the coroutines in that scope will be canceled when the onDestroy method of the
 activity invoked

-If it is in a fragment, all the coroutines in the fragments's scope will be canceled when the fragment’s
 onDestroy method invoked. So, we don’t have to manually create a job instance, add job instance to
 the scope’s context, override ondestroy method and write codes to clear the coroutines. 
 All those thing will be handled automatically by the ktx library. 

-So, for this lesson I am creating a new android studio project.
 This time I am selecting fragment + viewmodel option. Because this template will create an activity,
 a fragment connected to activity and a view model connected to the fragment.

-This will save our time. And I am naming this project as LifecycleDemo.
 You can see we have an activity, fragment and a view model.

-Here in the gradle, we already have lifecycle-extensions
 and lifecycle-viewmodel-ktx libraries added. In order to use new lifecyclescope
 we also need to add lifecycle-runtime-ktx library. I am adding it now.

-Also, I am changing these to the latest version. Since we are going to use this def arch_version
 statement, which is in single quotes, We need to make these double quotes.

-Also, for couroutines we need to add these dependencies.

-You can download this project form the resources of this lesson.

-So if you want you can easily copy these dependencies form that project. Let’s switch to the main
 activity now . Working with lifecycleScope is very straightforward. lifecycleScope
 dot launch

-This is it. As an example, you would use this coroutine to display a progress bar or some message.
 Let’s add a progress bar to the activity_main.xml file.

-I am setting the visibility as gone.

-I am going to make it visible within the coroutine.

-I am also setting the width and height as match parent.

-Let’s switch to the main activity. Here I am going to start the progress bar after 5 seconds and end it after 10 seconds. 
 We can use suspending function delay for that. Delay, 5000 milliseconds. Then progressBar.visibility equals View.VISIBLE
 then again delay for 10000 miliseconds.

-progressBar.visibility equals View.GONE

-So let’s just very quickly run that and see it in action .

-We should now see Progress bar starting after 5 seconds. And here we go
 This would rotate for 10 seconds. And it stoped . So , this works as we expected.

-This coroutine, runs in the main thread . If we want this coroutine to run in a separate thread we can easily define
 the context for the launch coroutine builder like this.

-Now this will run on a worker thread. Let’s remove this progress bar now. Because, it would cause an error if
 we try to call a progress bar form a worker thread .

-So for now, let's just and Let’s add a log message to see the current thread.
 I am going to test this now.

-Yes. as we intended thread is : DefaultDispatcher-worker-1. I am coping this code block.

-Now, let’s switch to our fragment. We can use this lifecycle scope in fragments too. All the active
 coroutines in the activity or fragment will automatically terminate just before the activity or fragment ends.

-Sometimes we might need to suspend execution of a code block , considering the current state of a lifecycle
 object. For that we have three additional builders. lifecycleScope. launchWhenCreated

-If you have some long running operations which should happen only once during the lifecycle of the activity
 or fragment you would use this coroutine. So this coroutine will launch , when the activity or fragment created for the first time.

-we also have lifecycleScope.launchWhenStarted

-so in this case, coroutine will launch when the activity or fragment started.

-Let’s say , we need to run a FragmentTransaction inside our coroutine scope,

-For that we need our Fragment lifecycle to be at least started .

-We can write that code inside

-this code block. And finally we have lifecycleScope.launchWhenResumed.

-This is the state in which the app interacts with the user.

-If we have a requirement to start a long running task just aftrer the app is up and running

-we would use this lifecycleScope.launchWhenResumed .

