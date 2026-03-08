# Chat User Guide

Chat is a **desktop chatbot app for managing tasks**, optimized for use via a Command Line Interface (CLI).

---

## Quick Start

1. Ensure you have Java `17` or above installed.
2. Download the latest `chat.jar` from the releases page.
3. Open a terminal and run:
```
   java -jar chat.jar
```
4. Type a command and press Enter to execute it.

---

## Features

> **Notes on command format:**
> - Words in `UPPER_CASE` are parameters you supply.
> - Parameters must be in the order shown.

---

### List all tasks: `list`
Shows all tasks currently in your list.

**Format:** `list`

**Example:**
```
list
```
```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][X] return book (by: June 6th)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
```

---

### Add a Todo: `todo`
Adds a task with no date or time.

**Format:** `todo DESCRIPTION`

**Example:**
```
todo read book
```
```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
```

---

### Add a Deadline: `deadline`
Adds a task that must be done by a specific date/time.

**Format:** `deadline DESCRIPTION /by DATE`

**Example:**
```
deadline return book /by June 6th
```
```
Got it. I've added this task:
  [D][ ] return book (by: June 6th)
Now you have 2 tasks in the list.
```

---

### Add an Event: `event`
Adds a task with a start and end time.

**Format:** `event DESCRIPTION /from START /to END`

**Example:**
```
event project meeting /from Mon 2pm /to 4pm
```
```
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
```

---

### Mark a task as done: `mark`
Marks the specified task as completed.

**Format:** `mark INDEX`
- `INDEX` refers to the number shown in the task list.

**Example:**
```
mark 2
```
```
Nice! I've marked this task as done:
  [D][X] return book (by: June 6th)
```

---

### Mark a task as not done: `unmark`
Marks the specified task as not yet completed.

**Format:** `unmark INDEX`

**Example:**
```
unmark 2
```
```
OK, I've marked this task as not done yet:
  [D][ ] return book (by: June 6th)
```

---

### Delete a task: `delete`
Removes the specified task from the list.

**Format:** `delete INDEX`

**Example:**
```
delete 1
```
```
Noted. I've removed this task:
  [T][ ] read book
Now you have 2 tasks in the list.
```

---

### Find tasks by keyword: `find`
Searches for tasks whose description contains the given keyword. The search is case-insensitive.

**Format:** `find KEYWORD`

**Example:**
```
find book
```
```
Here are the matching tasks in your list:
1.[T][ ] read book
2.[D][X] return book (by: June 6th)
```

---

### Exit the app: `bye`
Exits Chat. Your tasks are saved automatically.

**Format:** `bye`

---

## Saving your data
Tasks are saved automatically to `data/chat.txt` after every command. There is no need to save manually. The data will be loaded automatically the next time you start Chat.