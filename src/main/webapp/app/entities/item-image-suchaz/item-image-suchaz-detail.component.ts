import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { ItemImageSuchaz } from './item-image-suchaz.model';
import { ItemImageSuchazService } from './item-image-suchaz.service';

@Component({
    selector: 'jhi-item-image-suchaz-detail',
    templateUrl: './item-image-suchaz-detail.component.html'
})
export class ItemImageSuchazDetailComponent implements OnInit, OnDestroy {

    itemImage: ItemImageSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private itemImageService: ItemImageSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInItemImages();
    }

    load(id) {
        this.itemImageService.find(id)
            .subscribe((itemImageResponse: HttpResponse<ItemImageSuchaz>) => {
                this.itemImage = itemImageResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInItemImages() {
        this.eventSubscriber = this.eventManager.subscribe(
            'itemImageListModification',
            (response) => this.load(this.itemImage.id)
        );
    }
}
