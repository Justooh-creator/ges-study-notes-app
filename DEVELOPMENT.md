# GES Study Notes App - Development Guide

## Project Setup

### Prerequisites
- Java 8 or higher
- Android SDK (API 21+)
- Android Studio or Code on the Go
- Gradle 7.0+

### Build & Run

```bash
# Clone the repository
git clone https://github.com/Justooh-creator/ges-study-notes-app.git
cd ges-study-notes-app

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run on device/emulator
./gradlew installDebug
```

## Project Structure

### Activities (6 screens)
- **LoginActivity** - Sign in/Sign up
- **HomeActivity** - Main dashboard with 4 tabs
- **NotesListActivity** - Browse & search notes
- **NoteDetailActivity** - View single note
- **PastPapersActivity** - Filter & browse past papers
- **ProfileActivity** - User profile settings

### Fragments (4 tabs)
- **NotesFragment** - All study notes
- **PastPapersFragment** - All past papers
- **ProgressFragment** - Study progress tracker
- **SettingsFragment** - App settings & logout

### Database Layer
- **DatabaseHelper** - SQLite schema (5 tables)
- **NoteDao** - CRUD for notes
- **PastPaperDao** - CRUD for past papers
- **ProgressDao** - CRUD for study progress

### Services
- **SyncService** - Background sync with server
- **DownloadService** - File downloads
- **NotificationService** - Push notifications

### Data Models
- **User** - Student profile
- **Note** - Study material
- **PastPaper** - BECE exam paper
- **StudyProgress** - Track learning
- **Subject** - GES curriculum subject

## Database Schema

### users table
```sql
id (INTEGER PRIMARY KEY)
name (TEXT)
email (TEXT UNIQUE)
phone (TEXT)
student_class (TEXT)
school_name (TEXT)
created_at (TIMESTAMP)
updated_at (TIMESTAMP)
```

### notes table
```sql
id (INTEGER PRIMARY KEY)
title (TEXT)
subject (TEXT)
topic (TEXT)
content (TEXT)
author (TEXT)
file_path (TEXT)
is_downloaded (INTEGER)
created_at (TIMESTAMP)
updated_at (TIMESTAMP)
```

### past_papers table
```sql
id (INTEGER PRIMARY KEY)
subject (TEXT)
year (INTEGER)
exam_type (TEXT) -- BECE, Mock, etc
file_path (TEXT)
download_url (TEXT)
is_downloaded (INTEGER)
created_at (TIMESTAMP)
```

### study_progress table
```sql
id (INTEGER PRIMARY KEY)
user_id (INTEGER)
subject (TEXT)
topic (TEXT)
status (TEXT) -- not_started, in_progress, completed, reviewed
progress_percent (INTEGER)
last_studied (TIMESTAMP)
created_at (TIMESTAMP)
```

## GES Curriculum Support

### Supported Subjects (10)
1. English Language
2. Mathematics
3. Integrated Science
4. Social Studies
5. Religious and Moral Education (RME)
6. Ghanaian Language
7. Career Technology
8. Computing
9. Creative Arts and Design
10. French

### Class Levels (Basic 6-9)
- Basic 6 (Age 11-12)
- Basic 7 (Age 12-13)
- Basic 8 (Age 13-14)
- Basic 9 (Age 14-15)

## Features

### Core Features
- ✅ User authentication (login/signup)
- ✅ Browse study notes by subject
- ✅ Search notes by keywords
- ✅ Download notes for offline access
- ✅ Browse BECE past papers (2015-2023)
- ✅ Filter papers by subject/year
- ✅ Download past papers
- ✅ Track study progress
- ✅ Mark topics as studied
- ✅ View learning statistics
- ✅ Offline mode (cached data)
- ✅ Background sync
- ✅ Push notifications

### Future Features
- Quiz mode with instant feedback
- Leaderboard (class-wide rankings)
- Share notes with classmates
- Teacher uploads notes
- Study reminders
- Spaced repetition
- Performance analytics
- Exam countdown timer

## API Endpoints (Future)

```
GET  /api/notes
GET  /api/notes/:subject
POST /api/notes/search
GET  /api/past-papers
GET  /api/past-papers/:subject/:year
POST /api/progress
GET  /api/progress/:userId
GET  /api/quizzes
POST /api/quiz-answers
```

## File Locations

```
/data/data/com.example.gesstudynotes/
├── databases/
│   └── ges_study_notes.db
├── files/
│   ├── notes/
│   ├── past_papers/
│   └── cache/
└── shared_prefs/
    └── ges_study_notes_prefs.xml
```

## Testing

### Unit Tests
```bash
./gradlew test
```

### Instrumented Tests (Android Device)
```bash
./gradlew connectedAndroidTest
```

## Code Style
- Java 8+
- Follow Android naming conventions
- Use camelCase for variables/methods
- Use PascalCase for classes
- 4-space indentation

## Dependencies

- AndroidX (1.6.1)
- Material Design 3 (1.10.0)
- RecyclerView (1.3.1)
- Room/SQLite (2.5.2)
- OkHttp (4.10.0)
- Gson (2.10.1)
- WorkManager (2.8.1)

## Contributing

1. Create feature branch: `git checkout -b feature/your-feature`
2. Commit changes: `git commit -am 'Add new feature'`
3. Push to branch: `git push origin feature/your-feature`
4. Open Pull Request

## Troubleshooting

### App crashes on startup
- Check logcat: `adb logcat`
- Ensure permissions are granted
- Clear app data: `adb shell pm clear com.example.gesstudynotes`

### Database errors
- Delete app and reinstall
- Check DatabaseHelper.onUpgrade()
- Verify table schemas match model classes

### Download failures
- Check internet connection
- Verify file permissions (READ/WRITE_EXTERNAL_STORAGE)
- Check download URL validity

## Support

For issues and feature requests: https://github.com/Justooh-creator/ges-study-notes-app/issues

## License

MIT License - Free to use and modify

## Author

Justooh Creator
