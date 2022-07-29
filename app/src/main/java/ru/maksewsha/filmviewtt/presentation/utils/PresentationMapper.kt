package ru.maksewsha.filmviewtt.presentation.utils

import ru.maksewsha.filmviewtt.domain.model.LinkDomain
import ru.maksewsha.filmviewtt.domain.model.MultimediaDomain
import ru.maksewsha.filmviewtt.domain.model.ReviewDomain
import ru.maksewsha.filmviewtt.domain.model.ReviewDomainList
import ru.maksewsha.filmviewtt.domain.model.entity.ReviewDomainEntity
import ru.maksewsha.filmviewtt.domain.utils.EntityMapper
import ru.maksewsha.filmviewtt.presentation.model.LinkPresentation
import ru.maksewsha.filmviewtt.presentation.model.MultimediaPresentation
import ru.maksewsha.filmviewtt.presentation.model.ReviewPresentation
import ru.maksewsha.filmviewtt.presentation.model.ReviewPresentationList
import ru.maksewsha.filmviewtt.presentation.model.entity.ReviewPresentationEntity

class PresentationMapper : EntityMapper<ReviewPresentationEntity, ReviewDomainEntity> {
    override fun mapToEntity(from: ReviewDomainEntity) = when (from) {
        is ReviewDomainEntity.Success -> {
            ReviewPresentationEntity.Success(
                data = ReviewPresentationList(
                    status = from.data.status,
                    copyright = from.data.copyright,
                    has_more = from.data.has_more,
                    num_results = from.data.num_results,
                    results = from.data.results.map {
                        ReviewPresentation(
                            it.displayTitle,
                            it.mpaaRating,
                            it.criticsPick,
                            it.byline,
                            it.headline,
                            it.summaryShort,
                            it.publicationDate,
                            it.openingDate,
                            it.dateUpdated,
                            LinkPresentation(
                                it.linkData?.type,
                                it.linkData?.url,
                                it.linkData?.suggestedLinkText
                            ),
                            MultimediaPresentation(
                                it.multimediaData?.type,
                                it.multimediaData?.src,
                                it.multimediaData?.height,
                                it.multimediaData?.width
                            )
                        )
                    })
            )
        }
        is ReviewDomainEntity.Fail -> {
            ReviewPresentationEntity.Fail(
                errorMessage = from.errorMessage
            )
        }
    }

    override fun mapFromEntity(entity: ReviewPresentationEntity): ReviewDomainEntity {
        TODO("Not yet implemented")
    }
}