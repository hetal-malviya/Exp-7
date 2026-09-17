# Adaptive List-Detail Application Plan

Create an adaptive Android application using XML Views that displays a list of items and their details (images). The app will use `SlidingPaneLayout` to provide a responsive experience across different screen sizes.

## User Review Required

> [!NOTE]
> I will use `SlidingPaneLayout` which is the standard way to implement adaptive list-detail patterns in XML Views. On wider screens (tablets, foldables), the list and detail will be side-by-side. On narrow screens (phones), the detail will slide over the list.

## Proposed Changes

### Layouts

#### [MODIFY] [activity_main.xml](file:///C:/Users/Hetal Malviya/AndroidStudioProjects/Exp7/app/src/main/res/layout/activity_main.xml)
Replace the current layout with a `SlidingPaneLayout` containing a `ListView` (left pane) and an `ImageView` container (right pane).

#### [NEW] [list_item.xml](file:///C:/Users/Hetal Malviya/AndroidStudioProjects/Exp7/app/src/main/res/layout/list_item.xml)
Define the layout for items in the `ListView`.

### Logic

#### [MODIFY] [MainActivity.kt](file:///C:/Users/Hetal Malviya/AndroidStudioProjects/Exp7/app/src/main/java/com/example/exp_7/MainActivity.kt)
Implement the logic to:
- Populate the `ListView` with a list of items.
- Update the `ImageView` when an item is selected.
- Handle the `SlidingPaneLayout` state transitions.

## Verification Plan

### Manual Verification
1.  Deploy to a phone: Verify the `ListView` is shown first, and tapping an item slides the `ImageView` into view.
2.  Deploy to a tablet/resizable emulator: Verify the `ListView` and `ImageView` are shown side-by-side.
