# Contributing to GES Study Notes App

Thank you for your interest in contributing! Here's how you can help.

## How to Contribute

### 1. Report a Bug
- Check existing issues first
- Provide:
  - Device model and Android version
  - Steps to reproduce
  - Expected vs actual behavior
  - Logcat output

### 2. Suggest a Feature
- Describe the feature clearly
- Explain why it's useful
- Provide examples or mockups

### 3. Submit Code

#### Step 1: Fork & Clone
```bash
git clone https://github.com/Justooh-creator/ges-study-notes-app.git
cd ges-study-notes-app
```

#### Step 2: Create Feature Branch
```bash
git checkout -b feature/your-feature-name
```

#### Step 3: Make Changes
- Write clean, readable Java code
- Add comments for complex logic
- Follow Android best practices
- Test thoroughly

#### Step 4: Commit
```bash
git commit -am 'Add your feature description'
```

#### Step 5: Push & Create PR
```bash
git push origin feature/your-feature-name
```
Then open a Pull Request on GitHub

## Code Guidelines

### Naming Conventions
- **Classes**: PascalCase (e.g., `NotesActivity`)
- **Methods**: camelCase (e.g., `loadNotes()`)
- **Variables**: camelCase (e.g., `notesAdapter`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `DATABASE_VERSION`)
- **XML**: snake_case (e.g., `activity_login.xml`)

### Java Code Style
```java
public class NotesActivity extends AppCompatActivity {
    private static final String TAG = "NotesActivity";
    
    private RecyclerView notesRecyclerView;
    private NotesAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        initViews();
        setupRecyclerView();
    }
    
    private void initViews() {
        notesRecyclerView = findViewById(R.id.notesRecyclerView);
    }
    
    private void setupRecyclerView() {
        adapter = new NotesAdapter(notes);
        notesRecyclerView.setAdapter(adapter);
    }
}
```

### Comments
```java
// Single line comments for simple explanations

/**
 * Javadoc for public methods
 * 
 * @param userId The ID of the user
 * @return The user's study progress
 */
public int getUserProgress(int userId) {
    // Implementation here
}
```

## Testing Before PR

```bash
# Build the app
./gradlew build

# Run unit tests
./gradlew test

# Run on emulator
./gradlew installDebug

# Check for lint warnings
./gradlew lint
```

## PR Title Format

```
[TYPE] Brief description

Examples:
[FEATURE] Add quiz mode
[BUG FIX] Fix note download crash
[REFACTOR] Simplify sync logic
[DOCS] Update README
```

## What We Look For

✅ Clear, purposeful commits
✅ No breaking changes (unless discussed)
✅ Tested on multiple Android versions
✅ Proper error handling
✅ Code comments for complex logic
✅ Follows project conventions

❌ Large refactors without discussion
❌ Unused imports or variables
❌ No testing
❌ Inconsistent code style

## Questions?

Feel free to open an issue for discussion or contact the maintainers.

Happy coding! 🚀
