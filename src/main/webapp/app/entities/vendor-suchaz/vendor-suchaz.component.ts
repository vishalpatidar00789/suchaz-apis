import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { VendorSuchaz } from './vendor-suchaz.model';
import { VendorSuchazService } from './vendor-suchaz.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-vendor-suchaz',
    templateUrl: './vendor-suchaz.component.html'
})
export class VendorSuchazComponent implements OnInit, OnDestroy {
vendors: VendorSuchaz[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private vendorService: VendorSuchazService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.vendorService.query().subscribe(
            (res: HttpResponse<VendorSuchaz[]>) => {
                this.vendors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInVendors();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: VendorSuchaz) {
        return item.id;
    }
    registerChangeInVendors() {
        this.eventSubscriber = this.eventManager.subscribe('vendorListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
