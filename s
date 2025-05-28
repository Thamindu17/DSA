:- dynamic student/4.

% Initial Database Facts
student(john, 20, computer_science, 85).
student(mary, 19, mathematics, 92).
student(peter, 21, physics, 78).
student(susan, 20, chemistry, 88).
student(david, 22, computer_science, 90).


% Read a line of input as an atom
read_atom(Prompt, Atom) :-
    write(Prompt),
    flush_output,
    read_line_to_list(Chars),
    atom_codes(Atom, Chars).

% Read a number with validation (2 arguments)
read_number(Prompt, Number) :-
    repeat,
    read_atom(Prompt, InputAtom),
    (atom_number(InputAtom, Number) -> true ;
        (write('Invalid number! Try again: '), flush_output, fail)).

% Read number without prompt (1 argument) - CORRECTED
read_number(Number) :-
    repeat,
    read_line_to_list(Chars),
    atom_codes(Atom, Chars),
    (atom_number(Atom, Number) -> true ;
        (write('Invalid number! Try again: '), flush_output, fail)).


read_line_to_list(Chars) :-
    get_code(Char),
    read_line_to_list_aux(Char, Chars).

read_line_to_list_aux(10, []) :- !. % Newline
read_line_to_list_aux(-1, []) :- !. % EOF
read_line_to_list_aux(Char, [Char|Rest]) :-
    get_code(NextChar),
    read_line_to_list_aux(NextChar, Rest).

start :-
    write('========================================='), nl,
    write('    STUDENT MANAGEMENT SYSTEM'), nl,
    write('========================================='), nl,
    main_menu.

main_menu :-
    nl,
    write('Choose an option:'), nl,
    write('1. Add new student'), nl,
    write('2. Display all students'), nl,
    write('3. Find student by name'), nl,
    write('4. Calculate statistics'), nl,
    write('5. Grade students'), nl,
    write('6. Remove student'), nl,
    write('7. Exit'), nl,
    write('Enter your choice (1-7): '),
    flush_output,
    read_number(Choice),
    process_choice(Choice).

% ============================================================================
% MENU OPTION PROCESSING
% ============================================================================

process_choice(1) :- !, add_student.
process_choice(2) :- !, display_all_students.
process_choice(3) :- !, find_student.
process_choice(4) :- !, calculate_statistics.
process_choice(5) :- !, grade_all_students.
process_choice(6) :- !, remove_student.
process_choice(7) :- !,
    write('Thank you for using the system!'), nl,
    write('Goodbye!'), nl.

process_choice(_) :-
    write('Invalid choice! Please try again.'), nl,
    main_menu.

% ============================================================================
% CORE FUNCTIONALITY
% ============================================================================

add_student :-
    read_atom('Enter student name: ', Name),
    read_number('Enter age: ', Age),
    read_atom('Enter subjects: ', Subjects),
    read_number('Enter grade: ', Grade),
    (student(Name, _, _, _) ->
        (write('Student already exists!'), nl, main_menu)
    ;
        (assertz(student(Name, Age, Subjects, Grade)),
        write('Student added successfully!'), nl,
        main_menu)).

remove_student :-
    read_atom('Enter student name to remove: ', Name),
    (retract(student(Name, Age, Subjects, Grade)) ->
        format('Removed: ~w (Age: ~w, Subjects: ~w, Grade: ~w)~n',
              [Name, Age, Subjects, Grade])
    ;
        write('Student not found!')), nl,
    main_menu.

display_all_students :-
    write('All Students:'), nl,
    write('Name\t\tAge\tSubjects\t\tGrade'), nl,
    write('----------------------------------------'), nl,
    student(Name, Age, Subjects, Grade),
    format('~w\t\t~w\t~w\t\t~w~n', [Name, Age, Subjects, Grade]),
    fail.
display_all_students :- main_menu.

find_student :-
    read_atom('Enter student name: ', Name),
    (student(Name, Age, Subjects, Grade) ->
        (format('Student Found:~nName: ~w~nAge: ~w~nSubjects: ~w~nGrade: ~w~n',
               [Name, Age, Subjects, Grade]),
         (scholarship(Name, Amount) ->
             format('Scholarship: $~w~n', [Amount])
         ;
             write('No scholarship')), nl)
    ;
        (write('Student not found!'), nl)),
    main_menu.

calculate_statistics :-
    findall(Grade, student(_, _, _, Grade), Grades),
    (Grades \= [] ->
        (sum_list(Grades, Total),
         length(Grades, Count),
         Average is Total / Count,
         format('Statistics:~nTotal Students: ~w~nAverage Grade: ~1f~n',
               [Count, Average]),
         show_subjects_distribution)
    ;
        write('No students in database')), nl,
    main_menu.

show_subjects_distribution :-
    setof(Subjects, Name^Age^Grade^student(Name, Age, Subjects, Grade), SubjectsList),
    write('Subjects Distribution:'), nl,
    show_subjects(SubjectsList).

show_subjects([]).
show_subjects([Subjects|Rest]) :-
    findall(Name, student(Name, _, Subjects, _), Students),
    length(Students, Count),
    format('~w: ~w students~n', [Subjects, Count]),
    show_subjects(Rest).

% ============================================================================
% GRADING SYSTEM
% ============================================================================

grade_all_students :-
    write('Student Grades:'), nl,
    write('Name\t\tGrade\tClassification'), nl,
    write('----------------------------------------'), nl,
    student(Name, _, _, Grade),
    classify_grade(Grade, Classification),
    format('~w\t\t~w\t~w~n', [Name, Grade, Classification]),
    fail.
grade_all_students :- nl, main_menu.

classify_grade(Grade, 'Excellent') :- Grade >= 90, !.
classify_grade(Grade, 'Good') :- Grade >= 80, !.
classify_grade(Grade, 'Average') :- Grade >= 70, !.
classify_grade(Grade, 'Below Average') :- Grade >= 60, !.
classify_grade(_, 'Fail').

% ============================================================================
% UTILITY PREDICATES
% ============================================================================

sum_list([], 0).
sum_list([H|T], Sum) :- sum_list(T, Rest), Sum is H + Rest.
    