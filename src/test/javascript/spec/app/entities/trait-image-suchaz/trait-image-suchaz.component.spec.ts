/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuchazapisTestModule } from '../../../test.module';
import { TraitImageSuchazComponent } from '../../../../../../main/webapp/app/entities/trait-image-suchaz/trait-image-suchaz.component';
import { TraitImageSuchazService } from '../../../../../../main/webapp/app/entities/trait-image-suchaz/trait-image-suchaz.service';
import { TraitImageSuchaz } from '../../../../../../main/webapp/app/entities/trait-image-suchaz/trait-image-suchaz.model';

describe('Component Tests', () => {

    describe('TraitImageSuchaz Management Component', () => {
        let comp: TraitImageSuchazComponent;
        let fixture: ComponentFixture<TraitImageSuchazComponent>;
        let service: TraitImageSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [TraitImageSuchazComponent],
                providers: [
                    TraitImageSuchazService
                ]
            })
            .overrideTemplate(TraitImageSuchazComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TraitImageSuchazComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TraitImageSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TraitImageSuchaz(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.traitImages[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
