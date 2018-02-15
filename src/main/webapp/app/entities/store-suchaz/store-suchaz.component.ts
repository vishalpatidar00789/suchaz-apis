import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { StoreSuchaz } from './store-suchaz.model';
import { StoreSuchazService } from './store-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-store-suchaz',
    templateUrl: './store-suchaz.component.html'
})
export class StoreSuchazComponent implements OnInit, OnDestroy {
stores: StoreSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private storeService: StoreSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.storeService.query().subscribe(
            (res: HttpResponse<StoreSuchaz[]>) => {
                this.stores = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInStores();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: StoreSuchaz) {
        return item.id;
    }
    registerChangeInStores() {
        this.eventSubscriber = this.eventManager.subscribe('storeListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
