package ru.maksewsha.filmviewtt.data.utils

import ru.maksewsha.filmviewtt.data.model.entity.ReviewDataEntity
import ru.maksewsha.filmviewtt.domain.model.LinkDomain
import ru.maksewsha.filmviewtt.domain.model.MultimediaDomain
import ru.maksewsha.filmviewtt.domain.model.ReviewDomain
import ru.maksewsha.filmviewtt.domain.model.ReviewDomainList
import ru.maksewsha.filmviewtt.domain.model.entity.ReviewDomainEntity
import ru.maksewsha.filmviewtt.domain.utils.EntityMapper

class DataMapper : EntityMapper<ReviewDomainEntity, ReviewDataEntity> {
    override fun mapToEntity(from: ReviewDataEntity) = when (from) {
        is ReviewDataEntity.Success -> {
            ReviewDomainEntity.Success(
                data = ReviewDomainList(
                    status = from.data.status,
                    copyright = from.data.copyright,
                    has_more = from.data.has_more,
                    num_results = from.data.num_results,
                    results = from.data.results.map {
                        ReviewDomain(
                            it.displayTitle,
                            it.mpaaRating,
                            it.criticsPick,
                            it.byline,
                            it.headline,
                            it.summaryShort,
                            it.publicationDate,
                            it.openingDate,
                            it.dateUpdated,
                            LinkDomain(
                                it.linkData?.type,
                                it.linkData?.url,
                                it.linkData?.suggestedLinkText
                            ),
                            MultimediaDomain(
                                it.multimediaData?.type,
                                it.multimediaData?.src,
                                it.multimediaData?.height,
                                it.multimediaData?.width
                            )
                        )
                    }
                )
            )
        }
        is ReviewDataEntity.Fail -> {
            ReviewDomainEntity.Fail(
                errorMessage = from.errorMessage
            )
        }
    }

    override fun mapFromEntity(entity: ReviewDomainEntity): ReviewDataEntity {
        TODO("Not yet implemented")
    }
}