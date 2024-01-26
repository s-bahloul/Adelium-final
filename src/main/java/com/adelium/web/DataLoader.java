package com.adelium.web;

import com.adelium.web.entity.*;
import com.adelium.web.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.time.LocalDate;

@EnableTransactionManagement
@Configuration
@Service
@RequiredArgsConstructor
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final TrainingRepository trainingRepository;
    private final CategoryRepository categoryRepository;
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final SessionRepository sessionRepository;
    private final SessionCourseRepository sessionCourseRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private boolean isSetup = false;
    @Transactional
    public Role createRoleIfNotFound(String name) {

        Optional<Role> optionalRole = roleRepository.findByName(name);
        Role role = null;
        if (optionalRole.isEmpty()) {
            role = Role.builder().name(name).build();
            roleRepository.save(role);
        } else {
            role = optionalRole.get();
        }
        return role;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (isSetup) return;

//            // A supprimer
//            List<String> authoritiesData = List.of("READ_USER", "WRITE_USER", "DELETE_USER", "READ_ROLE", "WRITE_ROLE", "DELETE_ROLE", "READ_INSTRUCTOR", "WRITE_INSTRUCTOR", "DELETE_INSTRUCTOR", "WRITE_SESSION", "DELETE_SESSION", "WRITE_COURSE", "DELETE_COURSE", "READ_COURSE", "READ_TRAINING", "WRITE_TRAINING", "DELETE_TRAINING", "READ_CATEGORY", "WRITE_CATEGORY", "DELETE_CATEGORY");
//            authoritiesData.forEach( authority->
//                    authorityRepository.save(Authority.builder().name(authority).build())
//            );




            // adminAuthorities.stream().forEach(authorityRepository::save);

            List<String> listRole = List.of("ROLE_ADMIN", "ROLE_USER", "ROLE_INSTRUCTOR", "ROLE_TRAINING_MANAGER");
            listRole.forEach(this::createRoleIfNotFound);

            // Création et enregistrement du rôle "INSTRUCTOR_ROLE" avec les autorités associées
//            Role instructorRole = Role.builder()
//                    .name("INSTRUCTOR_ROLE")
//                    .build();
//
//            roleRepository.save(instructorRole);
//
//            // Création et enregistrement du rôle "USER_ROLE" avec les autorités associées
//
//            roleRepository.save(Role.builder().name("USER_ROLE").build());
//            roleRepository.save(Role.builder().name("ADMIN_ROLE").build());
//
            Role userRoleSaved = null;
            Role adminRoleSaved = null;

            try {
                userRoleSaved =
                        roleRepository
                                .findByName("ROLE_ADMIN")
                                .orElseThrow(() -> new Exception("ROLE_ADMIN n'existe pas"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try {
                adminRoleSaved =
                        roleRepository
                                .findByName("ROLE_USER")
                                .orElseThrow(() -> new Exception("ROLE_USER n'existe pas"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

    // Création et enregistrement des utilisateurs

            User user1 = User.builder()
                            .roles(Set.of(userRoleSaved))
                            .firstname("john")
                            .lastname("doe")
                            .username("john@adelium.com")
                            .password(passwordEncoder.encode("password"))
                            .build();
            User user2 = User.builder()
                            .roles(Set.of(adminRoleSaved))
                            .firstname("alice")
                            .lastname("smith")
                            .username("alice@adelium.com")
                            .password(passwordEncoder.encode("password"))
                            .build();

            // Enregistrez les utilisateurs dans la base de données

            userRepository.save(user1);
            userRepository.save(user2);

            // Créez et sauvegardez des instances de Category
            categoryRepository.save(Category.builder().name("INFORMATIQUE").description("Maîtriser les outils informatiques, TIC, gestion des systèmes - réseau, développement, méthodologie, base de données").build());
            categoryRepository.save(Category.builder().name("BUREAUTIQUE").description("Acquérir des compétences professionnelles variées et apprendre à se servir des différents logiciels de bureautique sur un ordinateur").build());
            categoryRepository.save(Category.builder().name("DIGITAL & MULTIMEDIA").description("Accéder à une douzaine de métiers dans le numérique, axés sur la communication en ligne, le développement web").build());
            categoryRepository.save(Category.builder().name("MANAGEMENT").description("Maîtriser la communication, la gestion du temps, la résolution de problèmes et le leadershi").build());
            categoryRepository.save(Category.builder().name("COMPETENCES METIER").description("Développer des connaissances, des compétences opérationnelles et des compétence comportementale").build());
            categoryRepository.save(Category.builder().name("UNIVERS PROFESSIONNEL").description("acquérir le savoir, le savoir-faire et le savoir-être ,capacité et aptitude").build());


            List<Category> categories = categoryRepository.findAll();


            // Créer de vraies formations réparties par catégorie

            Training training1 = new Training();
            training1.setName("Agilité");
            training1.setDescription("Maitrise de la methode agile et le travaille en équipe");

            training1.setCategory(categories.get(0)); // Remplacez l'index par l'index de votre catégorie Java

            Training training2 = new Training();
            training2.setName("Bases de données");
            training2.setDescription("Maitrise de la gestion et la conception de la BD et les languages adaptés");
            training2.setCategory(categories.get(0)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training3 = new Training();
            training3.setName("languages et developpement");
            training3.setDescription("Maitrise des different languages de conception et developpent informatique");
            training3.setCategory(categories.get(0)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training4 = new Training();
            training4.setName("Logiciels Bureautique");
            training4.setDescription("Maitrise des logiciels Bureautique");
            training4.setCategory(categories.get(1)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training5 = new Training();
            training5.setName("Retouches image");
            training5.setDescription("supprimer les défauts et faire des montages");
            training5.setCategory(categories.get(1)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training6 = new Training();
            training6.setName("Culture digitale");
            training6.setDescription("Réussire la transformation numérique de l'entreprise");
            training6.setCategory(categories.get(2)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training7 = new Training();
            training7.setName("Créer un site web");
            training7.setDescription("améliorer les performances digitales");
            training7.setCategory(categories.get(2)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training8 = new Training();
            training8.setName("Communication orale");
            training8.setDescription("Maitrise ensemble d'outils et de ressources technologiques");
            training8.setCategory(categories.get(3)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training9 = new Training();
            training9.setName("Organisation de travail et télétravail");
            training9.setDescription("Savoir optimiser son temps et ses priorités et son organisation");
            training9.setCategory(categories.get(3)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training10 = new Training();
            training10.setName("Gesion de projet");
            training10.setDescription("Processus de gestion planification et developpement des projets de technologies informatique ");
            training10.setCategory(categories.get(4)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training11 = new Training();
            training11.setName("Relation client");
            training11.setDescription("taches de gestion qui impliquent les interactions avec le client");
            training11.setCategory(categories.get(4)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training12 = new Training();
            training12.setName("Presse et médias");
            training12.setDescription("Moyen de distribution de diffusion ou de communication interpersonnelle");
            training12.setCategory(categories.get(5)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training13 = new Training();
            training13.setName("Immobilier");
            training13.setDescription("Compréhension du cadre juridique à la maîtrise des techniques de négociation");
            training13.setCategory(categories.get(5)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training14 = new Training();
            training14.setName("Savoir de base");
            training14.setDescription("Maitriser le Windows , le Word, Excel et Méssagerie électronique");
            training14.setCategory(categories.get(1)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training15 = new Training();
            training15.setName("Access");
            training15.setDescription("Maitriser le systéme de gestion de base de donnée ");
            training15.setCategory(categories.get(1)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training16 = new Training();
            training16.setName("Réseaux sociaux et content strategy");
            training16.setDescription("Faire de votre marque un média");
            training16.setCategory(categories.get(2)); // Remplacez l'index par l'index de votre catégorie Web Design

            Training training17 = new Training();
            training17.setName("Publication digitale");
            training17.setDescription("Maitriser l'interaction utilisateur");
            training17.setCategory(categories.get(2)); // Remplacez l'index par l'index de votre catégorie Web Design

            // Enregistrez les formations dans la base de données
            trainingRepository.save(training1);
            trainingRepository.save(training2);
            trainingRepository.save(training3);
            trainingRepository.save(training4);
            trainingRepository.save(training5);
            trainingRepository.save(training6);
            trainingRepository.save(training7);
            trainingRepository.save(training8);
            trainingRepository.save(training9);
            trainingRepository.save(training10);
            trainingRepository.save(training11);
            trainingRepository.save(training12);
            trainingRepository.save(training13);
            trainingRepository.save(training14);
            trainingRepository.save(training15);
            trainingRepository.save(training16);
            trainingRepository.save(training17);

            // Création des instructeurs
            Instructor instructor1 = new Instructor();
            instructor1.setFirstName("John");
            instructor1.setLastName("Doe");
            instructor1.setEmail("john.doe@gmail.com");
            instructor1.setBio("10 ans d'expérience dans la programmation Java.");

            // Instructeur 2
            Instructor instructor2 = new Instructor();
            instructor2.setFirstName("Alice");
            instructor2.setLastName("Smith");
            instructor2.setEmail("alice.smith@egmail.com");
            instructor2.setBio("Spécialiste en développement web et technologies front-end.");

// Instructeur 3
            Instructor instructor3 = new Instructor();
            instructor3.setFirstName("Bob");
            instructor3.setLastName("Johnson");
            instructor3.setEmail("bob.johnson@gmail.com");
            instructor3.setBio("Expert en bases de données et administration système.");

// Instructeur 4
            Instructor instructor4 = new Instructor();
            instructor4.setFirstName("Eva");
            instructor4.setLastName("Brown");
            instructor4.setEmail("eva.brown@gmail.com");
            instructor4.setBio("Passionnée par la conception UX/UI et les technologies émergentes.");

// Instructeur 5
            Instructor instructor5 = new Instructor();
            instructor5.setFirstName("Michael");
            instructor5.setLastName("Chen");
            instructor5.setEmail("michael.chen@gmail.com");
            instructor5.setBio("Spécialiste en sécurité informatique et gestion des risques.");

// Instructeur 6
            Instructor instructor6 = new Instructor();
            instructor6.setFirstName("Sophie");
            instructor6.setLastName("Miller");
            instructor6.setEmail("sophie.miller@gmail.com");
            instructor6.setBio("Experte en intelligence artificielle et apprentissage automatique.");

            instructorRepository.saveAll(List.of(instructor1, instructor2, instructor3, instructor4, instructor5, instructor6));

//            // Récupérer un instructeur et une catégorie existante pour le cours , je récupére ID du formateur concerné
//            //et de la catégorie concerné
//            Instructor instructor = instructorRepository.findById(1L).orElse(null);
//            Category category = categoryRepository.findById(1L).orElse(null);
//
//            // Récupérer une formation existante pour la session ET remplacez '1L' par l'ID d'une formation existante
//            Training training = trainingRepository.findById(1L).orElse(null);

            // Charger explicitement les collections
            List<Instructor> instructors = instructorRepository.findAll();
            List<Training> trainings = trainingRepository.findAll();



            //je vais utiliser la classe LocalDate de java pour remplacer la date
            LocalDate startDate = LocalDate.of(2024, 3, 1);
            LocalDate endDate = LocalDate.of(2024, 3, 31);

            // Cours 1
            Course course1 = Course.builder()
                    .title("Comprendre la démarche Agile")
                    .description("A l'issue de cette formation, vous serez capable de :\n" +
                            "\n" +
                            "Décrire ce qui différencie les approches prédictives des approches adaptatives\n" +
                            "Mettre en oeuvre les principales approches Agile (Scrum, Kanban, Lean...)\n" +
                            "Présenter un aperçu des éléments / techniques de base\n" +
                            "Décrire leur mise en oeuvre dans un projet.")

                    .online(true)
                    .price(100.0)
                    .categories(Set.of(categories.get(0)))
                    .build();



            // Enregistrez les cours dans la base de données
            courseRepository.save(course1);

            // Créer une session de cours
            SessionCourse sessionCourse1 = new SessionCourse();
            sessionCourse1.setCourse(course1);
            sessionCourseRepository.save(sessionCourse1);


            // Créer une session associée au cours et à la formation
            SessionTraining sessionTraining = new SessionTraining();
            sessionTraining.setTraining(trainings.get(0));  // Associer la session à une formation existante
            sessionTraining.getSessionCourses().add(sessionCourse1); // Associer la session au cours que vous venez de créer
//            System.out.println(sessionTraining);
            // Enregistrez la session dans la base de données
            sessionRepository.save(sessionTraining);

            isSetup = true;
    }
}

