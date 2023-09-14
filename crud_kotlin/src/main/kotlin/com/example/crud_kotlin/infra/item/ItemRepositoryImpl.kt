package com.example.crud_kotlin.infra.item

import com.example.crud_kotlin.domain.item.Item
import com.example.crud_kotlin.domain.item.ItemRepository
import com.example.crud_kotlin.domain.item.QItem.item
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class ItemRepositoryImpl(
    private val itemJpaRepository: ItemJpaRepository,
    private val queryFactory: JPAQueryFactory
) : ItemRepository {
    override fun findAllCustom(
        pageable: Pageable,
        keyword: String?,
        startDate: LocalDateTime?,
        endDate: LocalDateTime?
    ): Page<Item> {
        val content = queryFactory.selectFrom(item)
            .where(
                containsKeyword(keyword),
                betweenDate(startDate, endDate)
            )
            .orderBy(item.insertDate.desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val itemNos = queryFactory.select(item.itemNo)
            .from(item)
            .where(
                containsKeyword(keyword),
                betweenDate(startDate, endDate)
            )
            .fetch()

        return PageImpl(content, pageable, itemNos.size.toLong())
    }

    override fun save(item: Item): Long = itemJpaRepository.save(item).itemNo

    override fun saveAndFlush(item: Item) = itemJpaRepository.saveAndFlush(item)

    override fun findByIdOrNull(itemNo: Long): Item? = itemJpaRepository.findByIdOrNull(itemNo)

    override fun deleteAll() = itemJpaRepository.deleteAll()

    private fun containsKeyword(keyword: String?): BooleanExpression? =
        keyword?.let { item.name.contains(keyword) }

    private fun betweenDate(startDate: LocalDateTime?, endDate: LocalDateTime?): BooleanExpression? =
        startDate?.let {
            endDate?.let {
                item.insertDate.between(startDate, endDate)
            }
        }
}