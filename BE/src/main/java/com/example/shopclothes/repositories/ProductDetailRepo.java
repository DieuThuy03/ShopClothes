package com.example.shopclothes.repositories;

import com.example.shopclothes.dto.ProductDeatilsDTO;
import com.example.shopclothes.dto.ProductDetailResponseDto;
import com.example.shopclothes.entity.ProductDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface ProductDetailRepo extends JpaRepository<ProductDetail, Long> {

//    @Modifying
//    @Transactional
//    @Query(value = "update ProductDetail set status = 0 where id =?1", nativeQuery = true)
//    void delete(Long id);


//    @Query(value = "SELECT C.id, c.id_mate, c.id_product, c.id_cate, c.id_producer, c.id_size, c.id_col, c.code, c.quantity,\n" +
//            "            c.price, c.date_create, c.date_update, c.people_create, c.people_update, c.status\n" +
//            "            FROM product_detail C\n" +
//            "            JOIN (\n" +
//            "            SELECT product_detail.id_product, MIN(product_detail.id) AS min_id,\n" +
//            "            SUM(product_detail.quantity) AS quantity\n" +
//            "            FROM product_detail\n" +
//            "            JOIN product ON product.id = product_detail.id_product\n" +
//            "            where product_detail.status = 'DANG_HOAT_DONG'\n" +
//            "            GROUP BY product_detail.id_product\n" +
//            "            ) AS T\n" +
//            "            ON C.id_product = T.id_product AND C.id = T.min_id\n" +
//            "            JOIN product S ON S.id = C.id_product\n" +
//            "            order by c.date_create desc", nativeQuery = true)

//    @Query(value = "SELECT new com.example.shopclothes.dto.ProductDeatilsDTO(pd.id, i.name, pd.price, c.name, pd.quantity, pd.date_create, pd.status) " +
//            "FROM product_detail pd " +
//            "JOIN product p ON pd.id_product = p.id " +
//            "JOIN category c ON pd.id_cate = c.id " +
//            "LEFT JOIN imege i ON pd.id = i.id_ctsp " +
//            "ORDER BY pd.date_create DESC",
//            nativeQuery = true)

//    @Query("SELECT new com.example.shopclothes.dto.ProductDeatilsDTO (" +
//            "pd.id AS product_detail_id, " +
//            "pd.dateCreate AS product_detail_date_create, " +
//            "pd.price AS product_detail_price, " +
//            "pd.quantity AS product_detail_quantity, " +
//            "pd.code AS product_detail_code, " +
//            "pd.status AS product_detail_status, " +
//            "p.name AS product_name, " +
//            "c.name AS category_name, " +
//            "COALESCE(i.name, '') AS image_name) " +
//            "FROM ProductDetail pd " +
//            "JOIN pd.idProduct p " +
//            "JOIN pd.idCategory c " +
//            "LEFT JOIN pd.imeges i " +
//            "ORDER BY pd.dateCreate DESC")
//    public List<ProductDeatilsDTO> getAll();

//    @Query(value = "SELECT new com.example.ProductDeatilsDTO(p.id, i.name, p.price, c.name, p.quantity, p.dateCreate, p.status) \" +\n" +
//            "       \"FROM ProductDetail p \" +\n" +
//            "       \"JOIN p.idProduct pr \" +\n" +
//            "       \"JOIN p.idCategory c \" +\n" +
//            "       \"JOIN p.imeges i \" +\n" +
//            "       \"WHERE p.status = 'DANG_HOAT_DONG' \" +\n" +
//            "       \"ORDER BY p.dateCreate DESC", nativeQuery = true)

    @Query(value = "select sp from ProductDetail sp where sp.idProduct.id = :id")
    List<ProductDetail> detailByIdSP(Long id);

    @Transactional
    @Modifying
    @Query(value = "update ProductDetail c set c.quantity = :quantity where c.id = :id")
    void update(Integer quantity, Long id);

    @Query("SELECT new com.example.shopclothes.dto.ProductDetailResponseDto(  pd.id, p.name,i.imageLink, c.name,s.name,m.name,pd.quantity,pd.price, pd.status )" +
            "from ProductDetail pd " +
            "join Product p on pd.idProduct.id = p.id " +
            "join Imege i on i.product.id = p.id " +
            "join Color c on pd.idColor.id  = c.id " +
            "join Size s on pd.idSize.id = s.id " +
            "join Material m on pd.idMaterial.id = m.id " +
            "where pd.idProduct.id = :productId " +
            "and i.status = 'DANG_HOAT_DONG' " +
            "group by pd.id, i.imageLink, p.name, c.name, m.name,s.name,pd.quantity,pd.price, pd.status "+
            "ORDER BY pd.dateUpdate DESC")
    Page<ProductDetailResponseDto> findAllByProductId(@Param("productId") Long productId, Pageable pageable);
}
