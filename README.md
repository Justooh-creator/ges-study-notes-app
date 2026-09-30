# GES Study Notes & Past Papers Library

Android app for Basic 6-9 students in Ghana Education Service (GES) schools.

## Features
- 📚 Browse study notes by subject and topic
- 📄 Download past papers (BECE preparation)
- 📊 Track studied topics
- 🎯 Quiz mode with instant feedback
- 📱 Offline access to downloaded materials
- 🔍 Search notes and papers
- 👥 Share notes with classmates
- 📤 Teachers upload official notes

## Subjects Covered
- English Language
- Mathematics
- Integrated Science
- Social Studies
- Religious and Moral Education (RME)
- Ghanaian Language
- Career Technology
- Computing
- Creative Arts and Design
- French

## Tech Stack
- **Language:** Java
- **Target API:** Android 6.0+ (API 21+)
- **Database:** SQLite (local storage)
- **Backend:** Firebase (optional for cloud sync)
- **UI:** Android Material Design

## Project Structure
```
src/main/java/com/example/gesstudynotes/
├── MainActivity.java              # App entry point
├── activities/
│   ├── LoginActivity.java
│   ├── HomeActivity.java
│   ├── NotesListActivity.java
│   ├── NoteDetailActivity.java
│   ├── PastPapersActivity.java
│   ├── QuizActivity.java
│   └── ProfileActivity.java
├── fragments/
│   ├── NotesFragment.java
│   ├── PastPapersFragment.java
│   ├── ProgressFragment.java
│   └── SettingsFragment.java
├── models/
│   ├── User.java
│   ├── Note.java
│   ├── PastPaper.java
│   ├── Quiz.java
│   ├── StudyProgress.java
│   └── Subject.java
├── database/
│   ├── DatabaseHelper.java
│   ├── NoteDao.java
│   ├── PastPaperDao.java
│   └── ProgressDao.java
├── adapters/
│   ├── NotesAdapter.java
│   ├── PastPapersAdapter.java
│   └── QuizAdapter.java
├── services/
│   ├── SyncService.java
│   ├── DownloadService.java
│   └── NotificationService.java
├── utils/
│   ├── Constants.java
│   ├── FileUtils.java
│   ├── DateUtils.java
│   └── SharedPreferencesHelper.java
└── interfaces/
    ├── OnNoteClickListener.java
    └── SyncListener.java
```

## Getting Started

### Prerequisites
- Android Studio or Code on the Go
- Java 8 or higher
- Android SDK (API 21+)

### Installation
1. Clone the repository
2. Open in Android Studio or Code on the Go
3. Sync Gradle dependencies
4. Run the app on emulator or device

## Development Guide

### Building the APK
```bash
# Debug APK
./gradlew assembleDebug

# Release APK
./gradlew assembleRelease
```

### Running Tests
```bash
./gradlew test
```

## Database Schema

### Notes Table
```sql
CREATE TABLE notes (
    id INTEGER PRIMARY KEY,
    title TEXT NOT NULL,
    subject TEXT NOT NULL,
    topic TEXT NOT NULL,
    content TEXT,
    author TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    is_downloaded INTEGER DEFAULT 0,
    file_path TEXT
);
```

### Past Papers Table
```sql
CREATE TABLE past_papers (
    id INTEGER PRIMARY KEY,
    subject TEXT NOT NULL,
    year INTEGER NOT NULL,
    exam_type TEXT,
    file_path TEXT,
    download_url TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_downloaded INTEGER DEFAULT 0
);
```

### Study Progress Table
```sql
CREATE TABLE study_progress (
    id INTEGER PRIMARY KEY,
    user_id INTEGER,
    topic TEXT NOT NULL,
    status TEXT DEFAULT 'not_started',
    progress_percent INTEGER DEFAULT 0,
    last_studied TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## API Endpoints (Future)

- `GET /api/notes` - Fetch all notes
- `GET /api/notes/:subject` - Get notes by subject
- `GET /api/past-papers` - Fetch past papers
- `POST /api/progress` - Save study progress
- `GET /api/quizzes` - Fetch available quizzes

## Contributing

1. Create a feature branch (`git checkout -b feature/your-feature`)
2. Commit your changes (`git commit -am 'Add new feature'`)
3. Push to the branch (`git push origin feature/your-feature`)
4. Open a Pull Request

## License

MIT License - See LICENSE file for details

## Support

For issues and feature requests, please open an issue on GitHub.

## Authors

- Justooh Creator

## Acknowledgments

- GES Ghana Education Service
- Android Developer Community
