import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { ItemReviewSuchaz } from './item-review-suchaz.model';
import { ItemReviewSuchazService } from './item-review-suchaz.service';

@Component({
    selector: 'jhi-item-review-suchaz-detail',
    templateUrl: './item-review-suchaz-detail.component.html'
})
export class ItemReviewSuchazDetailComponent implements OnInit, OnDestroy {

    itemReview: ItemReviewSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private itemReviewService: ItemReviewSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInItemReviews();
    }

    load(id) {
        this.itemReviewService.find(id)
            .subscribe((itemReviewResponse: HttpResponse<ItemReviewSuchaz>) => {
                this.itemReview = itemReviewResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInItemReviews() {
        this.eventSubscriber = this.eventManager.subscribe(
            'itemReviewListModification',
            (response) => this.load(this.itemReview.id)
        );
    }
}
