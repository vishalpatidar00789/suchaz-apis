import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ItemSuchaz } from './item-suchaz.model';
import { ItemSuchazService } from './item-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-item-suchaz',
    templateUrl: './item-suchaz.component.html'
})
export class ItemSuchazComponent implements OnInit, OnDestroy {
items: ItemSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private itemService: ItemSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.itemService.query().subscribe(
            (res: HttpResponse<ItemSuchaz[]>) => {
                this.items = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInItems();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ItemSuchaz) {
        return item.id;
    }
    registerChangeInItems() {
        this.eventSubscriber = this.eventManager.subscribe('itemListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
