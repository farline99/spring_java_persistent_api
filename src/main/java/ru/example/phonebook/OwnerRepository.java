package ru.example.phonebook;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @EntityGraph(attributePaths = {"address", "phones"})
    @Query("""
            select distinct o from Owner o
            where (:lastName = '' or locate(lower(:lastName), lower(o.lastName)) > 0)
              and (:city = '' or locate(lower(:city), lower(o.address.city)) > 0)
              and (:number = '' or exists (
                  select p.id from Phone p
                  where p.owner = o and locate(:number, p.number) > 0))
            order by o.lastName, o.firstName, o.id
            """)
    List<Owner> search(@Param("lastName") String lastName,
            @Param("city") String city, @Param("number") String number);

    @Override
    @EntityGraph(attributePaths = {"address", "phones"})
    Optional<Owner> findById(Long id);
}
