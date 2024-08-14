
<h1>Alarm Clock Application</h1>

<h2>Overview</h2>
<p>This Alarm Clock application provides a user-friendly graphical user interface (GUI) for setting an alarm. The user can specify the time in hours, minutes, seconds, and AM/PM format. Once the set time is reached, an alarm sound will play, and a wake-up message will be displayed. The user can stop the alarm by clicking the stop button.</p>

<h2>Features</h2>
<ul>
    <li><strong>GUI for Setting Alarm Time:</strong> Easily set the alarm time using spinboxes for hours, minutes, seconds, and AM/PM.</li>
    <li><strong>Real-Time Alarm Checking:</strong> Continuously checks the current time against the set alarm time.</li>
    <li><strong>Alarm Sound and Wake-Up Message:</strong> Plays an alarm sound and displays a wake-up message when the alarm time is reached.</li>
    <li><strong>Stop Button:</strong> Allows the user to stop the alarm sound and close the wake-up message.</li>
</ul>

<h2>Requirements</h2>
<ul>
    <li>Python 3.x</li>
    <li>Pygame</li>
    <li>Tkinter</li>
</ul>

<h2>Installation</h2>
<ol>
  <li><strong>Install Required Packages:</strong>
      <pre><code>pip install pygame</code></pre>
  </li>
</ol>

<h2>Usage</h2>
<p>Run the following command to start the application:</p>
<pre><code>python alarm_clock.py</code></pre>

<ol>
    <li>Set the alarm time using the spinboxes for hours, minutes, seconds, and AM/PM.</li>
    <li>Click the "Submit" button to set the alarm.</li>
    <li>When the alarm time is reached, an alarm sound will play, and a wake-up message will be displayed.</li>
    <li>Click the "Stop" button to stop the alarm sound and close the wake-up message.</li>
</ol>

<h2>Code Explanation</h2>
<p>The main components of the code are as follows:</p>
<ul>
    <li><strong>TimeInputWindow Class:</strong> Handles the creation of the GUI for setting the alarm time.</li>
    <li><strong>submit_time Method:</strong> Captures the user input for the alarm time and initiates the alarm checking process.</li>
    <li><strong>set_alarm Method:</strong> Continuously checks the current time against the set alarm time and triggers the alarm when the time is reached.</li>
    <li><strong>alarm Method:</strong> Displays the wake-up message and plays the alarm sound.</li>
    <li><strong>stop Method:</strong> Stops the alarm sound and closes the wake-up message.</li>
</ul>

<h2>Contributing</h2>
<p>Contributions are welcome! Please open an issue or submit a pull request if you have any suggestions or improvements.</p>

<h2>License</h2>
<p>This project is licensed under the MIT License.</p>

<h2>Acknowledgements</h2>
<ul>
    <li>Tkinter for the GUI components</li>
    <li>Pygame for the sound functionality</li>
</ul>


    <h2>License</h2>
    <p>This project is licensed under the MIT License - see the <a href="LICENSE">LICENSE</a> file for details.</p>
</body>
</html>
