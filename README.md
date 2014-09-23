LogTimer
========
LogTimer is a simple android library to help you track time of small (or long) operations within Android with the use of the Shared Preferences and log the values to the LogCat.

How to install
==============
1. Download the library
2. Import as an android application
3. Add the LogTimer library to your project in Properties -> Android -> Add
4. You're ready to go

Usage
=====
LogTimer has 3 mainfunctions.

1. startTimer()
	- starts the timer
2. checkPoint()
	- checkpoints the timer, logging how much milliseconds have passed since last start or checkpoint.
3. stopTimer()
	- stops the timer
	
In these function calls a string can be specified to change the name of the timer.
<pre><code>LogTimer.getInstance(context).startTimer("usage_test");
Thread.sleep(100);
LogTimer.getInstance(context).checkPoint("usage_test");
Thread.sleep(100);
LogTimer.getInstance(context).checkPoint("usage_test");
Thread.sleep(100);
LogTimer.getInstance(context).checkPoint("usage_test");
Thread.sleep(100);
LogTimer.getInstance(context).stopTimer("usage_test");
</code></pre>
will log
<pre><code>>>>> Starting usage_test timer!
>> usage_test cp #1 108ms (108ms total) 
>> usage_test cp #2 112ms (220ms total) 
>> usage_test cp #3 117ms (337ms total) 
>>>> Timer usage_test finished -  : 115ms (452ms total)
</code></pre>

If no string is specified, the default string will be 'default_timer'

Credits and Contact
===============
This library was developed by Rui Santos (ruiapdsantos@gmail.com) as a tool to benchmark the same operations across different devices