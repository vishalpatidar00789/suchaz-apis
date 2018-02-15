import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { BuyLaterListSuchaz } from './buy-later-list-suchaz.model';
import { BuyLaterListSuchazService } from './buy-later-list-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-buy-later-list-suchaz',
    templateUrl: './buy-later-list-suchaz.component.html'
})
export class BuyLaterListSuchazComponent implements OnInit, OnDestroy {
buyLaterLists: BuyLaterListSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private buyLaterListService: BuyLaterListSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.buyLaterListService.query().subscribe(
            (res: HttpResponse<BuyLaterListSuchaz[]>) => {
                this.buyLaterLists = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInBuyLaterLists();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: BuyLaterListSuchaz) {
        return item.id;
    }
    registerChangeInBuyLaterLists() {
        this.eventSubscriber = this.eventManager.subscribe('buyLaterListListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
