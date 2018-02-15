import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ItemReviewSuchaz } from './item-review-suchaz.model';
import { ItemReviewSuchazService } from './item-review-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-item-review-suchaz',
    templateUrl: './item-review-suchaz.component.html'
})
export class ItemReviewSuchazComponent implements OnInit, OnDestroy {
itemReviews: ItemReviewSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private itemReviewService: ItemReviewSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.itemReviewService.query().subscribe(
            (res: HttpResponse<ItemReviewSuchaz[]>) => {
                this.itemReviews = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInItemReviews();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ItemReviewSuchaz) {
        return item.id;
    }
    registerChangeInItemReviews() {
        this.eventSubscriber = this.eventManager.subscribe('itemReviewListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
