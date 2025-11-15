# Online Education Platform

> Final project demonstrating multiple software design patterns in a cohesive Java application.

![UML](./assets/uml.svg)

## Table of Contents
1. Overview
2. Features
3. Design Patterns Implemented
4. Architecture
5. Code Structure
6. Domain Model
7. How Recommendations Work
8. How Course Upgrades Work
9. Running the Application (CLI)
10. Example CLI Session
11. Contributors
12. License

---

## 1. Overview
The Online Education Platform is a modular Java application that models courses, students, enrollment (implicit via subscription), announcements, and course recommendations. It is intentionally designed to showcase multiple classic GoF design patterns operating together in a realistic scenario.

The application provides:
- Course creation with content modules and quizzes.
- Dynamic course feature upgrades (certificate, extra materials).
- Student registration and course subscription (also counts as enrollment).
- Announcements to subscribed students.
- Two recommendation strategies (skill-based and popularity).
- A simple interactive CLI to exercise all patterns.

## 2. Features
- Create courses with arbitrary number of modules and quizzes.
- Upgrade any existing basic course to:
    - Certificated
    - Premium (certificate + extra material)
- Register students and set their skill level (1–10).
- Subscribe students to courses (implicitly enrolls them).
- Unsubscribe students from announcements (does not unenroll by default).
- Announce messages per course (Observer distribution).
- Recommend courses for a specific student based on skill.
- Recommend popular courses globally (based on number of enrolled students).
- View complete lists of courses and students, including derived features and enrollment counts.

## 3. Design Patterns Implemented
| Pattern Type    | Pattern         | Classes / Interfaces | Purpose |
|-----------------|-----------------|----------------------|---------|
| Creational      | Builder         | `BasicBuilder`, `CertificatedBuilder`, `PremiumBuilder`, `Builder` | Constructs complex `Course` objects incrementally (with modules, quizzes, difficulty, price). |
| Structural      | Decorator       | `CourseDecorator`, `CertificateCourseDecorator`, `ExtraMaterialCourseDecorator` | Dynamically augments a course with extra features and price adjustments without altering base class. |
| Structural      | Facade          | `EducationSystem`    | Provides a unified interface for managing courses, students, subscriptions, enrollments, recommendations, and announcements. |
| Creational      | Factory         | `StudentFactory`, `UserFactory` | Simplifies and centralizes creation of `Student` objects. |
| Behavioral      | Observer        | `AnnouncementPublisher`, `AnnouncementSubscriber`, `EducationSystem` (per-course subscription map), `Student` | Distributes course announcements only to subscribed students. |
| Behavioral      | Strategy        | `RecommendationStrategy`, `SkillBasedStrategy`, `PopularCourseStrategy` | Allows interchangeable recommendation algorithms (skill proximity vs popularity). |

All six patterns are fully implemented and actively used by the CLI.

## 4. Architecture
Core layered design:
- Domain: `Course`, `BasicCourse`, `Module`, `Quiz`, `Student`
- Patterns:
    - Builders produce base courses.
    - Decorators wrap courses post-creation to add features and modify pricing.
    - Facade (`EducationSystem`) coordinates domain actions and pattern interactions.
    - Strategies compute recommendations based on context (`RecommendationContext`).
    - Observer logic managed per-course (subscription sets) in `EducationSystem`.
    - Factory instantiates students with consistent construction semantics.
- CLI (`platform.app.Main`) invokes facade operations and pattern-specific workflows interactively.

## 5. Code Structure
```
src/main/java/platform/
├── app/
│   └── Main.java                 # CLI entry point
├── course/
│   ├── BasicCourse.java
│   ├── Course.java               # Course interface
│   ├── builder/
│   │   ├── Builder.java
│   │   ├── BasicBuilder.java
│   │   ├── CertificatedBuilder.java
│   │   └── PremiumBuilder.java
│   ├── decorator/
│   │   ├── CourseDecorator.java
│   │   ├── CertificateCourseDecorator.java
│   │   └── ExtraMaterialCourseDecorator.java
│   ├── module/
│   │   └── Module.java
│   ├── quiz/
│   │   └── Quiz.java
│   └── recommendation/
│       ├── RecommendationContext.java
│       ├── PopularCourseStrategy.java
│       ├── SkillBasedStrategy.java
│       └── interfaces/
│           └── RecommendationStrategy.java
├── facade/
│   └── EducationSystem.java
├── factory/
│   ├── UserFactory.java
│   └── StudentFactory.java
├── observer/
│   ├── AnnouncementPublisher.java
│   └── AnnouncementSubscriber.java
└── users/
    └── Student.java
    └── User.java
```

## 6. Domain Model
- `Course` holds: id, title, difficulty (1–10), price, modules (list of `Module`), quizzes (list of `Quiz`), feature flags, enrolled students.
- `Module` represents instructional content (title + text).
- `Quiz` represents assessment artifact (title + question count).
- `Student` has: id (UUID), skill (1–10), enrolled courses, reacts to announcements via Observer callback.

Upgrading a course via decorators adds feature tags:
- Certificate upgrade adds `"certificate"`.
- Extra material upgrade adds `"extra material"` (and can be combined with certificate for premium).
- Premium is effectively a stacked decorator chain: `ExtraMaterialCourseDecorator(CertificateCourseDecorator(course))`.

## 7. How Recommendations Work (Strategy)
- `RecommendationContext` provides:
    - `availableCourses`
    - `student` (nullable)
- `SkillBasedStrategy`:
    - Sorts courses by absolute distance from student’s skill level.
    - Returns a list prioritized by closeness (closest difficulty first).
- `PopularCourseStrategy`:
    - Filters out courses with zero enrolled students.
    - Sorts remaining by descending enrollment size.
      Used explicitly via menu options:
- Skill-based: option 6 (student-specific).
- Popular: option 7 (global popularity).
  Fallback (if implemented in your version of `EducationSystem`): skill-based may defer to popular if no matches.

## 8. How Course Upgrades Work (Decorator + Builder)
1. Create initial course with `BasicBuilder` (option 1).
2. Upgrade later using option 2:
    - Certificated: wraps with `CertificateCourseDecorator` (price increases by certificate fee).
    - Premium: wraps certificate decorator with `ExtraMaterialCourseDecorator` (adds both features and combined pricing).
3. Decorators preserve original enrolled students, modules, quizzes, etc., while changing price and features.

`PremiumBuilder` also exists to produce a premium course directly (builder chaining then decorator stacking internally).

## 9. Running the Application (CLI)
Requirements:
- JDK 21
- Maven

Build:
```bash
mvn clean package
```

Run:
```bash
java -jar target/platform-1.0-SNAPSHOT.jar
```

Menu Options:
| # | Action |
|---|--------|
| 1 | Create Basic Course (Builder) |
| 2 | Upgrade Course (Decorator stacking) |
| 3 | Register Student (Factory) |
| 4 | Subscribe Student to Course (Observer + implicit enrollment) |
| 5 | Unsubscribe Student from Course (Observer) |
| 6 | Recommend Course(s) for Student (Skill-Based Strategy) |
| 7 | Recommend Popular Course(s) (Popularity Strategy) |
| 8 | Announce Message to Course (Observer distribution) |
| 9 | List Courses |
| 10 | List Students |
| 0 | Exit |

Subscription automatically enrolls the student if not already enrolled.

## 10. Example CLI Session
```
=== Online Education Platform ===
1. Create Basic Course
...
Option: 1
Title: Intro to Patterns
Base price: 49.99
Difficulty (1-10): 5
How many modules? 2
  Module 1 title: Creational Patterns
  Module 1 content: Factory, Builder overview
  Module 2 title: Structural Patterns
  Module 2 content: Decorator, Facade notes
How many quizzes? 1
  Quiz 1 title: Pattern Basics
  Quiz 1 number of questions: 10
Created basic course 7f3d... (Intro to Patterns)

Option: 3
Student skill (1-10): 4
Registered student 2a91...

Option: 4
Student UUID: 2a91...
Course UUID: 7f3d...
Subscribed & enrolled 2a91... to course 7f3d...

Option: 2
Course UUID: 7f3d...
Upgrade type:
 1. Certificated
 2. Premium (Certificate + Extra Material)
Select: 2
Certificate fee: 20
Extra material fee: 10
How many extra materials? 2
  Extra material 1 name: Cheat Sheet
  Extra material 2 name: Case Studies
Upgraded to premium course.

Option: 6
Student UUID: 2a91...
Recommendations for 2a91... (skill=4):
  [C:7f3d...] Intro to Patterns diff=5 price=79.99 features=[certificate, extra material, extra:Cheat Sheet, extra:Case Studies] enrolled=1 modules=2 quizzes=1
```

## 11. Contributors
Team members (example mapping based on pattern ownership):
- Almat – Builder, Decorator
- Ilnur – Factory, Observer
- Sultan – Strategy, Facade

## 12. License
This project is licensed under the [MIT License](./LICENSE).

---

## Pattern Verification Checklist
- Builder: `BasicBuilder`, `CertificatedBuilder`, `PremiumBuilder`
- Decorator: `CertificateCourseDecorator`, `ExtraMaterialCourseDecorator`
- Factory: `StudentFactory`
- Observer: `AnnouncementPublisher` / per-course subscription in `EducationSystem`, `Student` consumes announcements.
- Facade: `EducationSystem`
- Strategy: `PopularCourseStrategy`, `SkillBasedStrategy`

All six patterns are integrated and exercised in CLI flows.
