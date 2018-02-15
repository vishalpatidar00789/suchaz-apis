import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { WishListSuchaz } from './wish-list-suchaz.model';
import { WishListSuchazService } from './wish-list-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-wish-list-suchaz',
    templateUrl: './wish-list-suchaz.component.html'
})
export class WishListSuchazComponent implements OnInit, OnDestroy {
wishLists: WishListSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private wishListService: WishListSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.wishListService.query().subscribe(
            (res: HttpResponse<WishListSuchaz[]>) => {
                this.wishLists = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInWishLists();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: WishListSuchaz) {
        return item.id;
    }
    registerChangeInWishLists() {
        this.eventSubscriber = this.eventManager.subscribe('wishListListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
