import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { BuyLaterListItemSuchaz } from './buy-later-list-item-suchaz.model';
import { BuyLaterListItemSuchazService } from './buy-later-list-item-suchaz.service';

@Component({
    selector: 'jhi-buy-later-list-item-suchaz-detail',
    templateUrl: './buy-later-list-item-suchaz-detail.component.html'
})
export class BuyLaterListItemSuchazDetailComponent implements OnInit, OnDestroy {

    buyLaterListItem: BuyLaterListItemSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private buyLaterListItemService: BuyLaterListItemSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBuyLaterListItems();
    }

    load(id) {
        this.buyLaterListItemService.find(id)
            .subscribe((buyLaterListItemResponse: HttpResponse<BuyLaterListItemSuchaz>) => {
                this.buyLaterListItem = buyLaterListItemResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBuyLaterListItems() {
        this.eventSubscriber = this.eventManager.subscribe(
            'buyLaterListItemListModification',
            (response) => this.load(this.buyLaterListItem.id)
        );
    }
}
