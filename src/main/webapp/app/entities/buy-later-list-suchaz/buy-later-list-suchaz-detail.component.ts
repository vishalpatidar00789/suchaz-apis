import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { BuyLaterListSuchaz } from './buy-later-list-suchaz.model';
import { BuyLaterListSuchazService } from './buy-later-list-suchaz.service';

@Component({
    selector: 'jhi-buy-later-list-suchaz-detail',
    templateUrl: './buy-later-list-suchaz-detail.component.html'
})
export class BuyLaterListSuchazDetailComponent implements OnInit, OnDestroy {

    buyLaterList: BuyLaterListSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private buyLaterListService: BuyLaterListSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBuyLaterLists();
    }

    load(id) {
        this.buyLaterListService.find(id)
            .subscribe((buyLaterListResponse: HttpResponse<BuyLaterListSuchaz>) => {
                this.buyLaterList = buyLaterListResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBuyLaterLists() {
        this.eventSubscriber = this.eventManager.subscribe(
            'buyLaterListListModification',
            (response) => this.load(this.buyLaterList.id)
        );
    }
}
