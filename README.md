# Simple Plugin

Simple plugin to display UI info on IDEA-based IDEs.

## How to Install

1. **Clone the repo and run Gradle task**
    - Use the Gradle `runIde` task to spin up a sandbox IDE with the plugin bundled.
    - ⚠️ It takes a long time to download sandbox IDE dependencies.
    - **Note**: You may need to click `File > Reload All from Disk` to restart the code analyzer after new sandbox IDE
   starts.
    

2. **Option two: manually Install a Plugin**
     - Get the latest release archived
     1. Go to `File > Settings > Plugins`. Click on the gear icon ⚙️
     2. Select `Install Plugin from Disk...` and **Restart IntelliJ IDEA**

# What it does

Gives three actions accessible from the `Tools > Custom Ideplugin` dropdown menu.

Action 1: Display the installed version of the Kotlin plugin.  
Action 2: Display all Gutter Icons present in the currently opened file.  
Action 3: Display information about the currently selected UI Component.  

## Checklist

1. **Verify the installed version of the Kotlin plugin**
   - Open the IDE with the plugin bundled and open any project.
   - Navigate to `Tools > Custom IDE Plugin > Display the IDE Plugin Version` and confirm that a dialog window pops up displaying the correct plugin version.

2. **Verify that gutter icons are present in the currently opened file**
   - Open any project with an overridden class.
   - Navigate to `Tools > Custom IDE Plugin > Display Gutter Icons` and confirm that a dialog window pops up displaying the list of gutter icons in the file.
   - Add more gutter icons by pressing the **Bookmark** key (F11). Then click `Display Gutter Icons` again and verify that the list has been updated.
   - Remove bookmarks, repeat the steps above, and confirm that the changes are reflected in the plugin dialog window.

3. **Verify the currently selected UI component**
   - Open the IDE with any project and select a UI item.
   - Navigate to `Tools > Custom IDE Plugin > Display Currently Selected UI Component` and confirm that a dialog window shows information about the selected UI item.
   - Repeat the same steps for different UI elements to verify accuracy.

4. **Verify that every dialog windows has all attributes**
   - Repeat the same steps as above.
   - Check that every plugin dialog window can be _closed, dragged, is visible_, and has _cancel/close_ buttons.

5. **Verify that the plugin is installable/uninstallable**
   - Install the plugin from a zip file.
   - Confirm that the plugin appears in `File > Settings > Plugins`.
   - Uninstall the plugin.
   - Confirm that the plugin disappears from the list.

## Other things to consider

The following items are out of scope for this checklist:
- Internationalization.
- Compatibility with other IDEs (WebStorm, PyCharm, etc.).
- Plugin publishing and the JetBrains Marketplace page.


