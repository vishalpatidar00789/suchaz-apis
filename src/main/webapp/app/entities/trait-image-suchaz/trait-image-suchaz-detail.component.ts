import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { TraitImageSuchaz } from './trait-image-suchaz.model';
import { TraitImageSuchazService } from './trait-image-suchaz.service';

@Component({
    selector: 'jhi-trait-image-suchaz-detail',
    templateUrl: './trait-image-suchaz-detail.component.html'
})
export class TraitImageSuchazDetailComponent implements OnInit, OnDestroy {

    traitImage: TraitImageSuchaz;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private traitImageService: TraitImageSuchazService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTraitImages();
    }

    load(id) {
        this.traitImageService.find(id)
            .subscribe((traitImageResponse: HttpResponse<TraitImageSuchaz>) => {
                this.traitImage = traitImageResponse.body;
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

    registerChangeInTraitImages() {
        this.eventSubscriber = this.eventManager.subscribe(
            'traitImageListModification',
            (response) => this.load(this.traitImage.id)
        );
    }
}
